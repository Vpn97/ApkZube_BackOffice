package com.apkzube.bo.service;

import com.apkzube.bo.entity.TutorialCategoryMst;
import com.apkzube.bo.repository.*;
import com.apkzube.bo.service.dto.ErrorDTO;
import com.apkzube.bo.service.dto.TutCategoryDTO;
import com.apkzube.bo.service.dto.TutorialCategoryFormDTO;
import com.apkzube.bo.service.mapper.MapperService;
import com.apkzube.bo.util.StringUtil;
import java.io.IOException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TutCategoryService {

    private final Logger log = LoggerFactory.getLogger(TutCategoryService.class);

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

    public List<TutorialCategoryMst> getAllTutCategoryByAppId(long appId) throws Exception {
        try {
            return tutorialCategoryMstRepository.findAllByAppId(appId);
        } catch (Exception e) {
            throw e;
        }
    }

    public TutCategoryDTO getTutCategoryById(long catId) {
        TutCategoryDTO tutCategoryDTO = null;

        if (catId != 0L) {
            TutorialCategoryMst mst = tutorialCategoryMstRepository.findOneByTutCatMstId(catId);
            if (mst != null) {
                tutCategoryDTO = mapperService.tutCategoryMstToDTO(mst);
            }
        }

        return tutCategoryDTO;
    }

    public List<ErrorDTO> createTutorialCategory(TutorialCategoryFormDTO formVM) throws IOException {
        List<ErrorDTO> errorDTOList = validateTutCategoryForm(formVM);

        if (errorDTOList.isEmpty()) {
            TutorialCategoryMst mst = new TutorialCategoryMst();
            mst.setCategoryName(formVM.getCategoryName().trim());
            mst.setCategoryDesc(formVM.getCategoryDesc().trim());
            mst.setCatType(formVM.getCatType());
            mst.setActive(formVM.isActive());
            mst.setAppId(formVM.getAppId());
            mst.setCreatedBy(userService.getCurrentUserId());
            mst.setCreatedDate(new Date());

            // TODO upload file

            String iconName = fileSystemService.saveIcon(formVM.getIconFile());
            if (StringUtil.isNullEmpty(iconName)) {
                errorDTOList.add(new ErrorDTO(messageSource.getMessage("icon.save.error", null, Locale.getDefault())));
            }

            String imageName = fileSystemService.saveImage(formVM.getImgFile());
            if (StringUtil.isNullEmpty(imageName)) {
                errorDTOList.add(new ErrorDTO(messageSource.getMessage("image.save.error", null, Locale.getDefault())));
            }

            mst.setIconURL(iconName);
            mst.setImgURL(imageName);

            if (!errorDTOList.isEmpty()) {
                return errorDTOList;
            }

            mst = tutorialCategoryMstRepository.save(mst);
            if (mst.getTutCatMstId() != null && mst.getTutCatMstId() != 0L) {
                return errorDTOList;
            } else {
                errorDTOList.add(new ErrorDTO("Enable to update category"));
            }
        }
        return errorDTOList;
    }

    public List<ErrorDTO> validateTutCategoryForm(TutorialCategoryFormDTO formVM) {
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        ErrorDTO errorDTO = null;
        try {
            if (formVM != null) {
                if (formVM.getAppId() != null && formVM.getAppId() != 0L) {
                    if (StringUtil.isNullEmpty(formVM.getCategoryName())) {
                        errorDTO = new ErrorDTO("Category name is required");
                        errorDTOList.add(errorDTO);
                    } else {
                        int count = tutorialCategoryMstRepository.countByCategoryNameAndAppId(
                            formVM.getCategoryName().trim(),
                            formVM.getAppId()
                        );
                        if (count != 0) {
                            errorDTO = new ErrorDTO("Category name already exist");
                            errorDTOList.add(errorDTO);
                        }
                    }

                    if (StringUtil.isNullEmpty(formVM.getCategoryDesc())) {
                        errorDTO = new ErrorDTO("Category description is required");
                        errorDTOList.add(errorDTO);
                    }

                    if (StringUtil.isNullEmpty(formVM.getCatType())) {
                        errorDTO = new ErrorDTO("Category type is required");
                        errorDTOList.add(errorDTO);
                    }

                    if (formVM.getImgFile() == null) {
                        errorDTO = new ErrorDTO("Image is required");
                        errorDTOList.add(errorDTO);
                    } else {
                        errorDTOList.addAll(fileSystemService.validateImageWithError(formVM.getImgFile()));
                    }

                    if (formVM.getIconFile() == null) {
                        errorDTO = new ErrorDTO("Icon is required");
                        errorDTOList.add(errorDTO);
                    } else {
                        errorDTOList.addAll(fileSystemService.validateIconWithError(formVM.getIconFile()));
                    }
                } else {
                    errorDTO = new ErrorDTO("Application is not selected");
                    errorDTOList.add(errorDTO);
                }
            }
        } catch (Exception e) {
            log.error("Error in validation of category VM ::: " + e.getMessage());
            errorDTO = new ErrorDTO("Error on validation of category");
            errorDTOList.add(errorDTO);
        }
        return errorDTOList;
    }

    public List<ErrorDTO> updateTutorialCategory(TutorialCategoryFormDTO formVM) throws IOException {
        List<ErrorDTO> errorDTOList = validateTutCategoryFormForUpdate(formVM);

        if (errorDTOList.isEmpty()) {
            TutorialCategoryMst mst = tutorialCategoryMstRepository.findOneByTutCatMstId(formVM.getTutCatMstId());
            mst.setCategoryName(formVM.getCategoryName().trim());
            mst.setCategoryDesc(formVM.getCategoryDesc().trim());
            mst.setCatType(formVM.getCatType());
            mst.setActive(formVM.isActive());
            mst.setUpdatedBy(userService.getCurrentUserId());
            mst.setUpdatedDate(new Date());

            // TODO upload file
            if (formVM.getImgFile() != null) {
                String imageName = fileSystemService.saveImage(formVM.getImgFile());
                if (StringUtil.isNullEmpty(imageName)) {
                    errorDTOList.add(new ErrorDTO(messageSource.getMessage("image.save.error", null, Locale.getDefault())));
                }

                mst.setImgURL(imageName);
            }

            if (formVM.getIconFile() != null) {
                String iconName = fileSystemService.saveIcon(formVM.getIconFile());
                if (StringUtil.isNullEmpty(iconName)) {
                    errorDTOList.add(new ErrorDTO(messageSource.getMessage("icon.save.error", null, Locale.getDefault())));
                    return errorDTOList;
                }
                mst.setIconURL(iconName);
            }

            if (!errorDTOList.isEmpty()) {
                return errorDTOList;
            }

            mst = tutorialCategoryMstRepository.save(mst);
            if (mst.getTutCatMstId() != null && mst.getTutCatMstId() != 0L) {
                return errorDTOList;
            } else {
                errorDTOList.add(new ErrorDTO("Enable to update category"));
            }
        }
        return errorDTOList;
    }

    public List<ErrorDTO> validateTutCategoryFormForUpdate(TutorialCategoryFormDTO formVM) {
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        ErrorDTO errorDTO = null;
        try {
            if (formVM != null) {
                if (formVM.getTutCatMstId() != null && formVM.getAppId() != 0L) {
                    Long catId = formVM.getTutCatMstId();
                    TutorialCategoryMst mst = tutorialCategoryMstRepository.findOneByTutCatMstId(catId);

                    if (mst != null) {
                        if (StringUtil.isNullEmpty(formVM.getCategoryName())) {
                            errorDTO = new ErrorDTO("Category name is required");
                            errorDTOList.add(errorDTO);
                        }

                        if (StringUtil.isNullEmpty(formVM.getCategoryDesc())) {
                            errorDTO = new ErrorDTO("Category description is required");
                            errorDTOList.add(errorDTO);
                        }

                        if (StringUtil.isNullEmpty(formVM.getCatType())) {
                            errorDTO = new ErrorDTO("Category type is required");
                            errorDTOList.add(errorDTO);
                        }

                        if (formVM.getIconFile() != null) {
                            errorDTOList.addAll(fileSystemService.validateIconWithError(formVM.getIconFile()));
                        }

                        if (formVM.getImgURL() != null) {
                            errorDTOList.addAll(fileSystemService.validateImageWithError(formVM.getImgFile()));
                        }
                    } else {
                        errorDTO = new ErrorDTO("Category not exist.");
                        errorDTOList.add(errorDTO);
                    }
                } else {
                    errorDTO = new ErrorDTO("Category not exist.");
                    errorDTOList.add(errorDTO);
                }
            }
        } catch (Exception e) {
            log.error("Error in validation of category VM ::: " + e.getMessage());
            errorDTO = new ErrorDTO("Error on validation of category");
            errorDTOList.add(errorDTO);
        }
        return errorDTOList;
    }
}
