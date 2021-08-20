package com.apkzube.bo.service;

import com.apkzube.bo.entity.AppMst;
import com.apkzube.bo.entity.TutorialCategoryMst;
import com.apkzube.bo.repository.*;
import com.apkzube.bo.util.StringUtil;
import com.apkzube.bo.web.rest.response.ErrorDTO;
import com.apkzube.bo.web.rest.response.TutCategoryMstDTO;
import com.apkzube.bo.web.rest.vm.TutorialCategoryFormVM;
import com.netflix.discovery.converters.Auto;
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

    public List<TutorialCategoryMst> getAllTutCategoryByAppId(long appId) throws Exception {
        try {
            return tutorialCategoryMstRepository.findAllByAppId(appId);
        } catch (Exception e) {
            throw e;
        }
    }

    public TutCategoryMstDTO getTutCategoryById(long catId) {
        TutCategoryMstDTO tutCategoryMstDTO = null;

        if (catId != 0L) {
            TutorialCategoryMst mst = tutorialCategoryMstRepository.findOneByTutCatMstId(catId);
            if (mst != null) {
                Optional<AppMst> appMst = appMstRepository.findById(mst.getAppId());
                tutCategoryMstDTO = new TutCategoryMstDTO(mst, appMst.get());
                tutCategoryMstDTO.setImgURL(fileSystemService.getImageURLByName(tutCategoryMstDTO.getImgURL()));
                tutCategoryMstDTO.setIconURL(fileSystemService.getIconURLByName(tutCategoryMstDTO.getIconURL()));

                if (mst.getCreatedBy() != null) {
                    String name = userRepository.getNameById(mst.getCreatedBy());
                    tutCategoryMstDTO.setCreatedUserName(name);
                }

                if (mst.getUpdatedBy() != null) {
                    String updatedUserName = userRepository.getNameById(mst.getUpdatedBy());
                    tutCategoryMstDTO.setUpdatedUserName(updatedUserName);
                }
            }
        }

        return tutCategoryMstDTO;
    }

    public HashMap<String, String> updateTutorialCategoryMst(TutorialCategoryMst tutorialCategoryMst) {
        try {
            List<ErrorDTO> errorDTOList = new ArrayList<>();
            TutorialCategoryMst mst = tutorialCategoryMstRepository.findOneByTutCatMstId(tutorialCategoryMst.getTutCatMstId());
            if (mst != null) {
                if (tutorialCategoryMst.getCategoryName() != null && !tutorialCategoryMst.getCategoryName().isEmpty()) {
                    mst.setCategoryName(tutorialCategoryMst.getCategoryName());
                } else {
                    errorDTOList.add(new ErrorDTO("CATEGORY", "Category name can't be empty"));
                }

                if (tutorialCategoryMst.getCategoryDesc() != null && !tutorialCategoryMst.getCategoryDesc().isEmpty()) {
                    mst.setCategoryName(tutorialCategoryMst.getCategoryName());
                } else {
                    errorDTOList.add(new ErrorDTO("CATEGORY", "Category description can't be empty"));
                }

                mst.setActive(true);
                //mst.setIconURL();

            }
        } catch (Exception e) {
            log.error("Error :: updateTutorialCategoryMst ::" + e.getMessage(), e);
        }
        return null;
    }

    public List<ErrorDTO> createTutorialCategory(TutorialCategoryFormVM formVM) throws IOException {
        List<ErrorDTO> errorDTOList = validateTutCategoryForm(formVM);

        if (errorDTOList.isEmpty()) {
            TutorialCategoryMst mst = new TutorialCategoryMst();
            mst.setCategoryName(formVM.getCategoryName());
            mst.setCategoryDesc(formVM.getCategoryDesc());
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

    public List<ErrorDTO> validateTutCategoryForm(TutorialCategoryFormVM formVM) {
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        ErrorDTO errorDTO = null;
        try {
            if (formVM != null) {
                if (formVM.getAppId() != null && formVM.getAppId() != 0L) {
                    if (StringUtil.isNullEmpty(formVM.getCategoryName())) {
                        errorDTO = new ErrorDTO("Category name is required");
                        errorDTOList.add(errorDTO);
                    } else {
                        int count = tutorialCategoryMstRepository.countByCategoryNameAndAppId(formVM.getCategoryName(), formVM.getAppId());
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

    public List<ErrorDTO> updateTutorialCategory(TutorialCategoryFormVM formVM) throws IOException {
        List<ErrorDTO> errorDTOList = validateTutCategoryFormForUpdate(formVM);

        if (errorDTOList.isEmpty()) {
            TutorialCategoryMst mst = tutorialCategoryMstRepository.findOneByTutCatMstId(formVM.getTutCatMstId());
            mst.setCategoryName(formVM.getCategoryName());
            mst.setCategoryDesc(formVM.getCategoryDesc());
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

    public List<ErrorDTO> validateTutCategoryFormForUpdate(TutorialCategoryFormVM formVM) {
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
