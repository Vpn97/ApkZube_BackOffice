package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.entity.AppMst;
import com.apkzube.bo.entity.TutorialCategoryMst;
import com.apkzube.bo.entity.TutorialCategoryType;
import com.apkzube.bo.repository.AppMstRepository;
import com.apkzube.bo.repository.TutorialCategoryTypeRepository;
import com.apkzube.bo.service.AppMstService;
import com.apkzube.bo.service.TutCategoryService;
import com.apkzube.bo.service.UserService;
import com.apkzube.bo.web.rest.response.AppMstInfoDTO;
import com.apkzube.bo.web.rest.response.ErrorDTO;
import com.apkzube.bo.web.rest.response.TutCategoryMstDTO;
import com.apkzube.bo.web.rest.vm.TutorialCategoryFormVM;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appmst")
public class AppMstController {

    private Logger log = LoggerFactory.getLogger(AppMstController.class);

    @Autowired
    private AppMstRepository appMstRepository;

    @Autowired
    private TutorialCategoryTypeRepository tutorialCategoryTypeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AppMstService appMstService;

    @Autowired
    private TutCategoryService tutCategoryService;

    @GetMapping("/getallappinfo")
    public ResponseEntity<List<AppMst>> getAllAppsInfo() {
        List<AppMst> appMsts = appMstRepository.findAll();
        return ResponseEntity.ok(appMsts);
    }

    @GetMapping("/findAppMstById")
    public ResponseEntity<AppMst> findAppMstById(@RequestParam(value = "appId") String appId) {
        try {
            Optional<AppMst> appMsts = appMstRepository.findById(Long.parseLong(appId));
            if (appMsts.isPresent()) {
                return ResponseEntity.ok(appMsts.get());
            }
        } catch (Exception e) {
            log.error("AppMstController : findAppMstById : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAppMstInfo")
    public ResponseEntity<AppMstInfoDTO> getAppMstInfo(@RequestParam(value = "appId") String appId) {
        try {
            AppMstInfoDTO appMstInfo = appMstService.getAppMstInfo(Long.parseLong(appId));
            return ResponseEntity.ok(appMstInfo);
        } catch (Exception e) {
            log.error("AppMstController : getAppMstInfo : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
