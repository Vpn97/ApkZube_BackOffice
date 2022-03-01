package com.apkzube.bo.service;

import com.apkzube.bo.entity.ActionCodeMst;
import com.apkzube.bo.entity.AppMaterialMst;
import com.apkzube.bo.entity.AppMaterialType;
import com.apkzube.bo.entity.AppMst;
import com.apkzube.bo.repository.*;
import com.apkzube.bo.service.dto.AppMaterialFormDTO;
import com.apkzube.bo.service.dto.AppMaterialMstDTO;
import com.apkzube.bo.service.dto.ErrorDTO;
import com.apkzube.bo.service.mapper.MapperService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaterialService {

    private final Logger log = LoggerFactory.getLogger(MaterialService.class);

    @Autowired
    private AppMaterialMstRepository appMaterialMstRepository;

    @Autowired
    private FileSystemService fileSystemService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppMaterialTypeRepository appMaterialTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppMstRepository appMstRepository;

    @Autowired
    private ActionCodeMstRepository actionCodeMstRepository;

    @Autowired
    private MapperService mapperService;

    public List<AppMaterialMstDTO> findAllByAppId(Long appId) {
        return appMaterialMstRepository.findAllByAppId(appId).stream().map(mapperService::appMaterialMstToDTO).collect(Collectors.toList());
    }

    public Optional<AppMaterialMstDTO> findById(Long matId) {
        return appMaterialMstRepository.findById(matId).map(mapperService::appMaterialMstToDTO);
    }

    public List<ErrorDTO> addMaterial(AppMaterialFormDTO materialVM) throws Exception {
        List<ErrorDTO> errorDTOList;
        try {
            errorDTOList = new ArrayList<>(validateForCreate(materialVM));

            if (errorDTOList.isEmpty()) {
                AppMaterialMst mst = new AppMaterialMst();
                mst.setActive(materialVM.isActive());
                mst.setCreatedBy(userService.getCurrentUserId());
                mst.setCreatedDate(new Date());
                mst.setMaterialURL(materialVM.getMaterialURL());
                mst.setIconURL(fileSystemService.saveIcon(materialVM.getIconFile()));
                mst.setAppId(materialVM.getAppId());
                mst.setTitle(materialVM.getTitle().trim());
                mst.setDetail(materialVM.getDetail().trim());
                mst.setTypeCode(materialVM.getTypeCode());
                mst.setClickActionCode(materialVM.getClickActionCode());

                mst = appMaterialMstRepository.save(mst);
                if (mst.getMaterialId() != null && mst.getMaterialId() != 0L) {
                    return errorDTOList;
                } else {
                    errorDTOList.add(new ErrorDTO("Enable to add material."));
                }
            }
        } catch (Exception e) {
            log.error("MaterialService :: addMaterial :: " + e.getMessage(), e);
            throw e;
        }

        return errorDTOList;
    }

    public List<ErrorDTO> updateMaterial(AppMaterialFormDTO materialVM) throws Exception {
        List<ErrorDTO> errorDTOList;
        try {
            errorDTOList = new ArrayList<>(validateForUpdate(materialVM));

            if (errorDTOList.isEmpty()) {
                Optional<AppMaterialMst> optionalAppMaterialMst = appMaterialMstRepository.findById(materialVM.getMaterialId());
                if (optionalAppMaterialMst.isPresent()) {
                    AppMaterialMst mst = optionalAppMaterialMst.get();
                    mst.setActive(materialVM.isActive());
                    mst.setUpdatedBy(userService.getCurrentUserId());
                    mst.setUpdatedDate(new Date());
                    mst.setMaterialURL(materialVM.getMaterialURL());

                    if (materialVM.getIconFile() != null) {
                        mst.setIconURL(fileSystemService.saveIcon(materialVM.getIconFile()));
                    }
                    mst.setAppId(materialVM.getAppId());
                    mst.setTitle(materialVM.getTitle());
                    mst.setDetail(materialVM.getDetail());
                    mst.setClickActionCode(materialVM.getClickActionCode());
                    mst.setTypeCode(materialVM.getTypeCode());

                    if (mst.getMaterialId() != null && mst.getMaterialId() != 0L) {
                        return errorDTOList;
                    } else {
                        errorDTOList.add(new ErrorDTO("Enable to update material."));
                    }
                } else {
                    errorDTOList.add(new ErrorDTO("Selected material not exist"));
                }
            }
        } catch (Exception e) {
            log.error("MaterialService :: updateMaterial :: " + e.getMessage(), e);
            throw e;
        }

        return errorDTOList;
    }

    public List<ErrorDTO> validateForCreate(AppMaterialFormDTO materialVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            if (materialVM != null) {
                if (materialVM.getIconFile() != null) {
                    fileSystemService.validateIconWithError(materialVM.getIconFile());
                } else {
                    errorDTOS.add(new ErrorDTO("Icon image is required."));
                }

                Optional<AppMst> appMst = appMstRepository.findById(materialVM.getAppId());
                if (appMst.isEmpty()) {
                    errorDTOS.add(new ErrorDTO("Application is not selected."));
                }

                List<AppMaterialType> appMaterialType = appMaterialTypeRepository.findAllByTypeCode(materialVM.getTypeCode());
                if (appMaterialType == null || appMaterialType.isEmpty()) {
                    errorDTOS.add(new ErrorDTO("type is required."));
                }

                List<ActionCodeMst> actionCodeMsts = actionCodeMstRepository.findAllByActionCode(materialVM.getClickActionCode());
                if (actionCodeMsts == null || actionCodeMsts.isEmpty()) {
                    errorDTOS.add(new ErrorDTO("action code is required."));
                }
            }
        } catch (Exception e) {
            log.error("Error :: validateForCreate :: " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error in validation of material data"));
        }

        return errorDTOS;
    }

    public List<ErrorDTO> validateForUpdate(AppMaterialFormDTO materialVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            if (materialVM != null) {
                if (materialVM.getIconFile() != null) fileSystemService.validateIconWithError(materialVM.getIconFile());

                Optional<AppMst> appMst = appMstRepository.findById(materialVM.getAppId());
                if (appMst.isEmpty()) {
                    errorDTOS.add(new ErrorDTO("Application is not selected."));
                }

                List<AppMaterialType> appMaterialType = appMaterialTypeRepository.findAllByTypeCode(materialVM.getTypeCode());
                if (appMaterialType == null || appMaterialType.isEmpty()) {
                    errorDTOS.add(new ErrorDTO("type is required."));
                }

                List<ActionCodeMst> actionCodeMsts = actionCodeMstRepository.findAllByActionCode(materialVM.getClickActionCode());
                if (actionCodeMsts == null || actionCodeMsts.isEmpty()) {
                    errorDTOS.add(new ErrorDTO("action code is required."));
                }
            }
        } catch (Exception e) {
            log.error("Error :: validateForUpdate :: " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error in validation of material data"));
        }

        return errorDTOS;
    }
}
