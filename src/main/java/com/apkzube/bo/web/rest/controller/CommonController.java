package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.entity.ActionCodeMst;
import com.apkzube.bo.entity.AppMaterialType;
import com.apkzube.bo.entity.TutorialCategoryType;
import com.apkzube.bo.repository.ActionCodeMstRepository;
import com.apkzube.bo.repository.AppMaterialMstRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    private final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ActionCodeMstRepository actionCodeMstRepository;

    @GetMapping("/fetchActionCodes")
    public ResponseEntity<List<ActionCodeMst>> fetchActionCodes() {
        try {
            return ResponseEntity.ok(actionCodeMstRepository.findAll());
        } catch (Exception e) {
            log.error("CommonController : fetchActionCodes : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
