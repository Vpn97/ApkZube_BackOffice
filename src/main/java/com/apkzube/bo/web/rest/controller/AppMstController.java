package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.entity.AppMst;
import com.apkzube.bo.repository.AppMstRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appmst")
public class AppMstController {

    private Logger log = LoggerFactory.getLogger(AppMstController.class);

    @Autowired
    private AppMstRepository appMstRepository;

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
}
