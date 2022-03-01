package com.apkzube.bo.service.mapper;

import com.apkzube.bo.entity.*;
import com.apkzube.bo.repository.*;
import com.apkzube.bo.service.FileSystemService;
import com.apkzube.bo.service.UserService;
import com.apkzube.bo.service.dto.*;
import com.apkzube.bo.util.CommonUtil;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MapperService {

    private final Logger log = LoggerFactory.getLogger(MapperService.class);

    @Autowired
    private FileSystemService fileSystemService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppMstRepository appMstRepository;

    @Autowired
    ProgrammingLangMstRepository programmingLangMstRepository;

    public AppMaterialMstDTO appMaterialMstToDTO(AppMaterialMst materialMst) {
        AppMaterialMstDTO dto = null;
        if (materialMst != null) {
            dto = new AppMaterialMstDTO();
            dto.setMaterialId(materialMst.getMaterialId());
            dto.setTitle(materialMst.getTitle());
            dto.setActive(materialMst.isActive());
            dto.setAppId(materialMst.getAppId());
            dto.setDetail(materialMst.getDetail());
            dto.setTypeCode(materialMst.getTypeCode());
            dto.setClickActionCode(materialMst.getClickActionCode());
            dto.setCreatedDate(materialMst.getCreatedDate());
            dto.setUpdatedDate(materialMst.getUpdatedDate());

            dto.setMaterialURL(materialMst.getMaterialURL());

            if (CommonUtil.isValidURL(materialMst.getIconURL())) {
                dto.setIconURL(materialMst.getIconURL());
            } else {
                dto.setIconURL(fileSystemService.getIconURLByName(materialMst.getIconURL()));
            }

            dto.setCreatedBy(userService.getUserNameById(materialMst.getCreatedBy()));
            dto.setUpdatedBy(userService.getUserNameById(materialMst.getUpdatedBy()));
        }
        return dto;
    }

    public TutCategoryDTO tutCategoryMstToDTO(TutorialCategoryMst categoryMst) {
        TutCategoryDTO dto = null;
        if (categoryMst != null) {
            dto = new TutCategoryDTO();
            if (categoryMst.getAppId() != null) {
                Optional<AppMst> appMst = appMstRepository.findById(categoryMst.getAppId());
                if (appMst.isPresent()) dto.setAppMst(appMst.get());
            }
            dto.setTutCatMstId(categoryMst.getTutCatMstId());
            dto.setAppId(categoryMst.getAppId());
            dto.setCategoryName(categoryMst.getCategoryName());
            dto.setCategoryDesc(categoryMst.getCategoryDesc());
            dto.setCatType(categoryMst.getCatType());
            dto.setActive(categoryMst.isActive());
            dto.setCreatedBy(categoryMst.getCreatedBy());
            dto.setCreatedDate(categoryMst.getCreatedDate());
            dto.setUpdatedBy(categoryMst.getUpdatedBy());
            dto.setImgURL(fileSystemService.getImageURLByName(categoryMst.getImgURL()));
            dto.setIconURL(fileSystemService.getIconURLByName(categoryMst.getIconURL()));
            dto.setCreatedUserName(userService.getUserNameById(categoryMst.getCreatedBy()));
            dto.setUpdatedUserName(userService.getUserNameById(categoryMst.getUpdatedBy()));
        }
        return dto;
    }

    public PracticeProgramDTO practiceProgramMstToDTO(PracticeProgramMst mst) {
        PracticeProgramDTO dto = null;
        if (mst != null) {
            dto = new PracticeProgramDTO();
            dto.setProgramId(mst.getProgramId());
            dto.setAppId(mst.getAppId());
            dto.setCatId(mst.getCatId());
            dto.setProgramBy(mst.getProgramBy());
            dto.setTitle(mst.getTitle());
            dto.setProgramText(mst.getProgramText());
            dto.setOutputText(mst.getOutputText());
            dto.setProgramInput(mst.getProgramInput());
            dto.setExplainText(mst.getExplainText());
            dto.setProgramURL(mst.getProgramURL());
            dto.setCreatedDate(mst.getCreatedDate());
            dto.setUpdatedDate(mst.getUpdateDate());
            dto.setCreatedBy(userService.getUserNameById(mst.getCreatedBy()));
            dto.setUpdatedBy(userService.getUserNameById(mst.getUpdatedBy()));
        }
        return dto;
    }

    public ProgrammingLangDTO programmingLangMstTODTO(ProgrammingLangMst mst) {
        ProgrammingLangDTO dto = null;
        if (mst != null) {
            dto = new ProgrammingLangDTO();
            dto.setLangId(mst.getLangId());
            dto.setLangName(mst.getLangName());
            dto.setLangMode(mst.getLangMode());
            dto.setLangTheme(mst.getLangTheme());
            dto.setMimeType(mst.getMimeType());
            dto.setIconURL(fileSystemService.getIconUrlWithUrlCheck(mst.getIconURL()));
            dto.setImgURL(fileSystemService.getImageUrlWithUrlCheck(mst.getImgURL()));
            dto.setCreatedDate(mst.getCreatedDate());
            dto.setUpdatedDate(mst.getUpdatedDate());
            dto.setCreatedBy(userService.getUserNameById(mst.getCreatedBy()));
            dto.setUpdatedBy(userService.getUserNameById(mst.getUpdatedBy()));
        }
        return dto;
    }

    public AppMstDTO appMstToDTO(AppMst mst) {
        AppMstDTO dto = null;
        if (mst != null) {
            dto = new AppMstDTO();
            dto.setAppId(mst.getAppId());
            dto.setLangId(mst.getLangId());
            dto.setAppName(mst.getAppName());
            dto.setPackageName(mst.getPackageName());
            dto.setAppPrice(mst.getAppPrice());
            dto.setAppSize(mst.getAppSize());
            dto.setAppLink(mst.getAppLink());
            dto.setIconUrl(fileSystemService.getIconUrlWithUrlCheck(mst.getIconUrl()));
            dto.setDeveloperName(mst.getDeveloperName());
            dto.setBlogBaseUrl(mst.getBlogBaseUrl());
            dto.setPrivacyPolicyUrl(mst.getPrivacyPolicyUrl());
            dto.setCreatedDate(mst.getCreatedDate());

            if (mst.getLangId() != null && mst.getLangId() != 0L) {
                dto.setProgrammingLangDTO(programmingLangMstTODTO(programmingLangMstRepository.findOneByLangId(mst.getLangId())));
            }
        }
        return dto;
    }

    public TutorialCategoryDTO tutorialCategoryMstToDTO(TutorialCategoryMst categoryMst) {
        TutorialCategoryDTO dto = null;
        if (categoryMst != null) {
            dto = new TutorialCategoryDTO();
            dto.setAppId(categoryMst.getAppId());
            dto.setCategoryDesc(categoryMst.getCategoryDesc());
            dto.setCategoryName(categoryMst.getCategoryName());
            dto.setActive(categoryMst.isActive());
            dto.setCatType(categoryMst.getCatType());
            dto.setIconURL(fileSystemService.getIconUrlWithUrlCheck(categoryMst.getIconURL()));
            dto.setImgURL(fileSystemService.getImageUrlWithUrlCheck(categoryMst.getImgURL()));
            dto.setTutCatMstId(categoryMst.getTutCatMstId());
            dto.setCreatedDate(categoryMst.getCreatedDate());
            dto.setUpdatedDate(categoryMst.getUpdatedDate());
            dto.setCreatedBy(userService.getUserNameById(categoryMst.getCreatedBy()));
            dto.setUpdatedBy(userService.getUserNameById(categoryMst.getUpdatedBy()));
        }
        return dto;
    }

    public TutorialMstDTO tutorialMstToDTO(TutorialMst tutorialMst) {
        TutorialMstDTO dto = null;
        if (tutorialMst != null) {
            dto = new TutorialMstDTO();
            dto.setTutMstId(tutorialMst.getTutMstId());
            dto.setTutCatMstId(tutorialMst.getTutCatMstId());
            dto.setDescription(tutorialMst.getDescription());
            dto.setTitle(tutorialMst.getTitle());
            dto.setImgURL(fileSystemService.getImageUrlWithUrlCheck(tutorialMst.getImgURL()));
            dto.setIconURL(fileSystemService.getIconUrlWithUrlCheck(tutorialMst.getIconURL()));
            dto.setActive(tutorialMst.isActive());
            dto.setCreatedDate(tutorialMst.getCreatedDate());
            dto.setUpdatedDate(tutorialMst.getUpdatedDate());
            dto.setCreatedBy(userService.getUserNameById(tutorialMst.getCreatedBy()));
            dto.setUpdatedBy(userService.getUserNameById(tutorialMst.getUpdatedBy()));
        }
        return dto;
    }

    public TutorialDtlDTO tutorialDtlToDTO(TutorialDtl tutorialDtl) {
        TutorialDtlDTO dto = null;
        if (tutorialDtl != null) {
            dto = new TutorialDtlDTO();
            dto.setTutDtlId(tutorialDtl.getTutDtlId());
            dto.setTutMstId(tutorialDtl.getTutMstId());
            dto.setTitle(tutorialDtl.getTitle());
            dto.setDescription(tutorialDtl.getDescription());
            dto.setActive(tutorialDtl.isActive());
            dto.setCreatedDate(tutorialDtl.getCreatedDate());
            dto.setUpdatedDate(tutorialDtl.getUpdatedDate());
            dto.setCreatedBy(userService.getUserNameById(tutorialDtl.getCreatedBy()));
            dto.setUpdatedBy(userService.getUserNameById(tutorialDtl.getUpdatedBy()));
        }
        return dto;
    }
}
