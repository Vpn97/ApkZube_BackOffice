package com.apkzube.bo.service;

import static com.apkzube.bo.service.constant.TutorialConstant.CAT_TYPE_TUTORIAL;

import com.apkzube.bo.entity.AppMst;
import com.apkzube.bo.repository.*;
import com.apkzube.bo.service.constant.TutorialConstant;
import com.apkzube.bo.service.dto.AppMstInfoDTO;
import com.apkzube.bo.service.mapper.MapperService;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppMstService {

    private final Logger log = LoggerFactory.getLogger(AppMstService.class);

    @Autowired
    private UserRepository userRepository;

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

    public AppMstInfoDTO getAppMstInfo(long appId) {
        AppMstInfoDTO appMstInfoDTO = new AppMstInfoDTO();
        Optional<AppMst> appMstOptional = appMstRepository.findById(appId);
        if (appMstOptional.isPresent()) {
            AppMst appMst = appMstOptional.get();

            appMstInfoDTO.setAppMstDTO(mapperService.appMstToDTO(appMst));

            int totalCategory = tutorialCategoryMstRepository.countByAppId(appId);
            appMstInfoDTO.setTotalCategory(totalCategory);

            int totalTutorial = tutorialMstRepository.totalTutorial(appId, CAT_TYPE_TUTORIAL);
            appMstInfoDTO.setTotalTutorial(totalTutorial);

            int totalProgramCat = programCategoryMstRepository.countByAppId(appId);
            appMstInfoDTO.setTotalProgramCategory(totalProgramCat);

            int totalExample = programMstRepository.countByAppId(appId);
            appMstInfoDTO.setTotalExample(totalExample);

            int totalMaterial = appMaterialMstRepository.countByAppId(appId);
            appMstInfoDTO.setTotalMaterial(totalMaterial);
        }

        return appMstInfoDTO;
    }
}
