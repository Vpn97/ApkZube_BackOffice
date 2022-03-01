package com.apkzube.bo.service;

import static com.apkzube.bo.service.constant.TutorialConstant.CAT_TYPE_TUTORIAL;

import com.apkzube.bo.entity.TutorialMst;
import com.apkzube.bo.repository.*;
import com.apkzube.bo.service.dto.ErrorDTO;
import com.apkzube.bo.service.dto.TutorialCategoryDTO;
import com.apkzube.bo.service.dto.TutorialDtlDTO;
import com.apkzube.bo.service.dto.TutorialMstFormDTO;
import com.apkzube.bo.service.mapper.MapperService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TutorialService {

    private final Logger log = LoggerFactory.getLogger(TutorialService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private FileSystemService fileSystemService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppMstRepository appMstRepository;

    @Autowired
    private ProgramCategoryMstRepository programCategoryMstRepository;

    @Autowired
    private PracticeProgramMstRepository programMstRepository;

    @Autowired
    private TutorialMstRepository tutorialMstRepository;

    @Autowired
    private TutorialCategoryMstRepository tutorialCategoryMstRepository;

    @Autowired
    private TutorialContentMstRepository tutorialContentMstRepository;

    @Autowired
    private BlogpostMstRepository blogpostMstRepository;

    @Autowired
    private TutorialDtlRepository tutorialDtlRepository;

    @Autowired
    private AppMaterialMstRepository appMaterialMstRepository;

    @Autowired
    private MapperService mapperService;

    public List<TutorialCategoryDTO> categoryWithTutorialList(long appId) {
        List<TutorialCategoryDTO> tutorialCategoryDTOS = tutorialCategoryMstRepository
            .findAllByAppIdAndCatType(appId, CAT_TYPE_TUTORIAL)
            .stream()
            .map(mapperService::tutorialCategoryMstToDTO)
            .collect(Collectors.toList());

        tutorialCategoryDTOS.forEach(
            tutorialCategoryDTO -> {
                tutorialCategoryDTO.setTutorialMstDTOS(
                    tutorialMstRepository
                        .findAllByTutCatMstId(tutorialCategoryDTO.getTutCatMstId())
                        .stream()
                        .map(mapperService::tutorialMstToDTO)
                        .collect(Collectors.toList())
                );
            }
        );

        return tutorialCategoryDTOS;
    }

    public List<ErrorDTO> saveTutorialMst(TutorialMstFormDTO formVM) throws Exception {
        List<ErrorDTO> errorDTOS = null;
        boolean isUpdate = false;
        try {
            isUpdate = (formVM.getTutMstId() != null && formVM.getTutMstId() != 0L);
            errorDTOS = validateTutorialMstFormVM(formVM, isUpdate);
            if (errorDTOS.isEmpty()) {
                TutorialMst mst = null;
                if (isUpdate) {
                    mst = tutorialMstRepository.getOne(formVM.getTutMstId());
                    mst.setUpdatedBy(userService.getCurrentUserId());
                    mst.setUpdatedDate(new Date());
                } else {
                    mst = new TutorialMst();
                    mst.setCreatedBy(userService.getCurrentUserId());
                    mst.setCreatedDate(new Date());
                }

                mst.setTutCatMstId(formVM.getTutCatMstId());
                mst.setActive(formVM.isActive());
                mst.setTitle(formVM.getTitle().trim());
                mst.setDescription(formVM.getDescription().trim());
                mst.setImgURL(fileSystemService.saveImage(formVM.getImgFile()));
                mst = tutorialMstRepository.save(mst);

                if (mst.getTutMstId() != null && mst.getTutMstId() != 0L) {
                    return errorDTOS;
                } else {
                    if (isUpdate) {
                        errorDTOS.add(new ErrorDTO("Enable to create tutorial mst."));
                    } else {
                        errorDTOS.add(new ErrorDTO("Enable to update tutorial mst."));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error :: TutorialService :: saveTutorialMst :: " + e.getMessage(), e);
            throw e;
        }
        return errorDTOS;
    }

    private List<ErrorDTO> validateTutorialMstFormVM(TutorialMstFormDTO formVM, boolean isUpdate) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            if (!tutorialCategoryMstRepository.existsById(formVM.getTutCatMstId())) {
                errorDTOS.add(new ErrorDTO("Tutorial category is not exist"));
            }

            if (isUpdate) {
                //validate for update
                if (!tutorialMstRepository.existsById(formVM.getTutMstId())) {
                    errorDTOS.add(new ErrorDTO("Selected tutorial is not exist"));
                }
            } else {
                //validate for create
                if (formVM.getImgFile() != null) {
                    fileSystemService.validateImageWithError(formVM.getImgFile());
                } else {
                    errorDTOS.add(new ErrorDTO("Background image is required."));
                }
            }
        } catch (Exception e) {
            log.error("Error :: validation of tutorial save :: " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error to validate tutorial mst form"));
        }

        return errorDTOS;
    }

    public List<TutorialDtlDTO> getAllTutorialDtlsByMstId(@NotNull Long mstId) {
        return tutorialDtlRepository.findAllByTutMstId(mstId).stream().map(mapperService::tutorialDtlToDTO).collect(Collectors.toList());
    }
}
