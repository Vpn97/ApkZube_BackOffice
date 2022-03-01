package com.apkzube.bo.service;

import com.apkzube.bo.entity.*;
import com.apkzube.bo.repository.*;
import com.apkzube.bo.service.dto.ErrorDTO;
import com.apkzube.bo.service.dto.PracticeExampleFormDTO;
import com.apkzube.bo.service.dto.PracticeProgramDTO;
import com.apkzube.bo.service.dto.ProgramCategoryDTO;
import com.apkzube.bo.service.dto.ProgramCategoryFormDTO;
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
public class ExamplesService {

    private final Logger log = LoggerFactory.getLogger(ExamplesService.class);

    @Autowired
    private PracticeProgramMstRepository programMstRepository;

    @Autowired
    private ProgramCategoryMstRepository programCategoryMstRepository;

    @Autowired
    private FileSystemService fileSystemService;

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppMstRepository appMstRepository;

    @Autowired
    MapperService mapperService;

    public List<ProgramCategoryDTO> getProgramCategories(Long appId) {
        return programCategoryMstRepository.findAllByAppId(appId).stream().map(this::programCategoryToDto).collect(Collectors.toList());
    }

    public ProgramCategoryDTO programCategoryToDto(ProgramCategoryMst categoryMst) {
        ProgramCategoryDTO programCategoryDTO = null;
        if (categoryMst != null) {
            programCategoryDTO = new ProgramCategoryDTO(categoryMst);
            programCategoryDTO.setCatIconURL(commonService.getIconURL(categoryMst.getCatIconURL()));
            programCategoryDTO.setCreatedBy(userService.getUserNameById(categoryMst.getCreatedBy()));
            programCategoryDTO.setUpdatedBy(userService.getUserNameById(categoryMst.getUpdatedBy()));

            int totalExamples = programMstRepository.countByCatId(categoryMst.getProgramCatId());
            programCategoryDTO.setTotalExamples(totalExamples);
        }
        return programCategoryDTO;
    }

    public ProgramCategoryDTO getProgramCategory(Long catId) {
        return programCategoryToDto(programCategoryMstRepository.findOneByProgramCatId(catId));
    }

    public List<ErrorDTO> saveOrUpdateCategory(ProgramCategoryFormDTO catVM, boolean isUpdate) throws Exception {
        List<ErrorDTO> errorDTOList;
        try {
            errorDTOList = new ArrayList<>(validate(catVM, isUpdate));

            if (errorDTOList.isEmpty()) {
                ProgramCategoryMst mst = null;
                if (isUpdate) {
                    Optional<ProgramCategoryMst> categoryMstOptional = programCategoryMstRepository.findById(catVM.getProgramCatId());
                    if (categoryMstOptional.isPresent()) {
                        mst = categoryMstOptional.get();
                        mst.setUpdatedBy(userService.getCurrentUserId());
                        mst.setUpdatedDate(new Date());
                    } else {
                        errorDTOList.add(new ErrorDTO("selected category not exist"));
                        return errorDTOList;
                    }
                } else {
                    mst = new ProgramCategoryMst();
                    mst.setAppId(catVM.getAppId());
                    mst.setCreatedBy(userService.getCurrentUserId());
                    mst.setCreatedDate(new Date());
                }

                mst.setActive(catVM.isActive());

                if (catVM.getIconFile() != null) {
                    mst.setCatIconURL(fileSystemService.saveIcon(catVM.getIconFile()));
                }

                mst.setCatName(catVM.getCatName().trim());
                mst.setCatDescription(catVM.getCatDescription().trim());

                mst = programCategoryMstRepository.save(mst);
                if (mst.getProgramCatId() != null && mst.getProgramCatId() != 0L) {
                    return errorDTOList;
                } else {
                    if (isUpdate) {
                        errorDTOList.add(new ErrorDTO("Enable to create program category."));
                    } else {
                        errorDTOList.add(new ErrorDTO("Enable to update program category."));
                    }
                }
            }
        } catch (Exception e) {
            log.error("MaterialService :: addCategory :: " + e.getMessage(), e);
            throw e;
        }
        return errorDTOList;
    }

    private List<ErrorDTO> validate(ProgramCategoryFormDTO catVM, boolean isUpdate) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            if (catVM != null) {
                if (!isUpdate) {
                    if (catVM.getIconFile() != null) {
                        fileSystemService.validateIconWithError(catVM.getIconFile());
                    } else {
                        errorDTOS.add(new ErrorDTO("Icon image is required."));
                    }

                    int countByCatName = programCategoryMstRepository.countByCatName(catVM.getCatName().trim());
                    if (countByCatName > 0) {
                        errorDTOS.add(new ErrorDTO("Category name already exist"));
                    }
                } else {
                    // validate for update
                    if (catVM.getProgramCatId() == null || catVM.getProgramCatId() == 0L) {
                        errorDTOS.add(new ErrorDTO("selected category not exist"));
                    }
                }

                Optional<AppMst> appMst = appMstRepository.findById(catVM.getAppId());
                if (appMst.isEmpty()) {
                    errorDTOS.add(new ErrorDTO("Application is not selected."));
                }
            }
        } catch (Exception e) {
            log.error("Error :: validateForCreate :: " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error in validation of program category data"));
        }

        return errorDTOS;
    }

    public List<PracticeProgramDTO> getExamplesByCategory(Long catId) {
        return programMstRepository.findAllByCatId(catId).stream().map(mapperService::practiceProgramMstToDTO).collect(Collectors.toList());
    }

    public PracticeProgramDTO programById(Long programId) {
        return programMstRepository.findById(programId).map(mapperService::practiceProgramMstToDTO).orElseThrow();
    }

    public List<ErrorDTO> saveOrUpdateProgramExample(PracticeExampleFormDTO formVM) {
        List<ErrorDTO> errorDTOList = null;
        boolean isUpdate = false;
        try {
            if (formVM != null) {
                isUpdate = (formVM.getProgramId() != null && formVM.getProgramId() != 0L);

                errorDTOList = validateExampleFormVM(formVM, isUpdate);

                if (errorDTOList.isEmpty()) {
                    PracticeProgramMst mst = null;
                    if (isUpdate) {
                        mst = programMstRepository.getOne(formVM.getProgramId());
                        mst.setUpdatedBy(userService.getCurrentUserId());
                        mst.setUpdateDate(new Date());
                    } else {
                        mst = new PracticeProgramMst();
                        mst.setCreatedBy(userService.getCurrentUserId());
                        mst.setCreatedDate(new Date());
                    }

                    mst.setAppId(formVM.getAppId());
                    mst.setCatId(formVM.getCatId());
                    mst.setProgramText(formVM.getProgramText());
                    mst.setTitle(formVM.getTitle().trim());
                    mst.setOutputText(formVM.getOutputText());

                    if (formVM.getProgramInput() != null) {
                        mst.setProgramInput(formVM.getProgramInput().trim());
                    }

                    if (formVM.getProgramURL() != null) {
                        mst.setProgramURL(formVM.getProgramURL().trim());
                    }

                    mst.setExplainText(formVM.getExplainText());
                    mst.setProgramBy(formVM.getProgramBy());

                    mst = programMstRepository.save(mst);
                    if (mst.getProgramId() != null && mst.getProgramId() != 0L) {
                        return errorDTOList;
                    } else {
                        if (isUpdate) {
                            errorDTOList.add(new ErrorDTO("Enable to create program example."));
                        } else {
                            errorDTOList.add(new ErrorDTO("Enable to update program example."));
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error on creating program example :: " + e.getMessage(), e);
            throw e;
        }

        return errorDTOList;
    }

    private List<ErrorDTO> validateExampleFormVM(PracticeExampleFormDTO formVM, boolean isUpdate) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();

        try {
            if (!programCategoryMstRepository.existsById(formVM.getCatId())) {
                errorDTOS.add(new ErrorDTO("Program category is not exist"));
            }

            if (formVM.getAppId() != null && formVM.getAppId() != 0L) {
                if (!appMstRepository.existsById(formVM.getAppId())) {
                    errorDTOS.add(new ErrorDTO("Selected application is nor exist"));
                }
            }

            if (isUpdate) {
                if (!programMstRepository.existsById(formVM.getProgramId())) {
                    errorDTOS.add(new ErrorDTO("Practice example is not exist"));
                }
            }
        } catch (Exception e) {
            log.error("Error :: validation of example save :: " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error to validate example data"));
        }

        return errorDTOS;
    }
}
