package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.entity.TutorialCategoryMst;
import com.apkzube.bo.entity.TutorialCategoryType;
import com.apkzube.bo.repository.AppMstRepository;
import com.apkzube.bo.repository.TutorialCategoryTypeRepository;
import com.apkzube.bo.service.AppMstService;
import com.apkzube.bo.service.TutCategoryService;
import com.apkzube.bo.service.UserService;
import com.apkzube.bo.web.rest.response.ErrorDTO;
import com.apkzube.bo.web.rest.response.TutCategoryMstDTO;
import com.apkzube.bo.web.rest.vm.TutorialCategoryFormVM;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private Logger log = LoggerFactory.getLogger(CategoryController.class);

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

    @GetMapping("/getAllTutCategoryByAppId")
    public ResponseEntity<List<TutorialCategoryMst>> getAllTutCategoryByAppId(@RequestParam(value = "appId") String appId) {
        try {
            List<TutorialCategoryMst> categoryMsts = tutCategoryService.getAllTutCategoryByAppId(Long.parseLong(appId));
            return ResponseEntity.ok(categoryMsts);
        } catch (Exception e) {
            log.error("AppMstController : findTutCategoryByAppId : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getTutCategoryById")
    public ResponseEntity<TutCategoryMstDTO> getTutCategoryById(@RequestParam(value = "catId") String catId) {
        try {
            TutCategoryMstDTO categoryMst = tutCategoryService.getTutCategoryById(Long.parseLong(catId));
            return ResponseEntity.ok(categoryMst);
        } catch (Exception e) {
            log.error("AppMstController : getTutCategoryById : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/fetchTutCatTypes")
    public ResponseEntity<List<TutorialCategoryType>> fetchTutCatTypes() {
        try {
            return ResponseEntity.ok(tutorialCategoryTypeRepository.findAll());
        } catch (Exception e) {
            log.error("AppMstController : fetchTutCatTypes : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/createTutorialCategory", consumes = "multipart/form-data")
    public ResponseEntity<?> createTutorialCategory(@Valid @ModelAttribute TutorialCategoryFormVM formVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            errorDTOS = tutCategoryService.createTutorialCategory(formVM);
            if (errorDTOS.isEmpty()) {
                return ResponseEntity.created(URI.create("/createTutorialCategory")).build();
            }

            return ResponseEntity.badRequest().body(errorDTOS);
        } catch (Exception e) {
            log.error("AppMstController : createTutorialCategory : " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error on create category"));
        }
        return ResponseEntity.internalServerError().body(errorDTOS);
    }

    @PostMapping(value = "/updateTutorialCategory", consumes = "multipart/form-data")
    public ResponseEntity<?> updateTutorialCategory(@Valid @ModelAttribute TutorialCategoryFormVM formVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            errorDTOS = tutCategoryService.updateTutorialCategory(formVM);
            if (errorDTOS.isEmpty()) {
                return ResponseEntity.created(URI.create("/updateTutorialCategory")).build();
            }

            return ResponseEntity.badRequest().body(errorDTOS);
        } catch (Exception e) {
            log.error("AppMstController : updateTutorialCategory : " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error on create updated"));
        }
        return ResponseEntity.internalServerError().body(errorDTOS);
    }
}
