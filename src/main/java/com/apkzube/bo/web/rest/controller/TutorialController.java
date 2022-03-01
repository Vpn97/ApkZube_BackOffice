package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.repository.AppMstRepository;
import com.apkzube.bo.repository.TutorialCategoryTypeRepository;
import com.apkzube.bo.repository.TutorialMstRepository;
import com.apkzube.bo.service.AppMstService;
import com.apkzube.bo.service.TutCategoryService;
import com.apkzube.bo.service.TutorialService;
import com.apkzube.bo.service.UserService;
import com.apkzube.bo.service.dto.ErrorDTO;
import com.apkzube.bo.service.dto.TutorialCategoryDTO;
import com.apkzube.bo.service.dto.TutorialDtlDTO;
import com.apkzube.bo.service.dto.TutorialMstDTO;
import com.apkzube.bo.service.dto.TutorialMstFormDTO;
import com.apkzube.bo.service.mapper.MapperService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutorial")
public class TutorialController {

    private Logger log = LoggerFactory.getLogger(TutorialController.class);

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

    @Autowired
    private MapperService mapperService;

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private TutorialMstRepository tutorialMstRepository;

    @GetMapping("/categoryWithTutorialList")
    public ResponseEntity<List<TutorialCategoryDTO>> categoryWithTutorialList(@RequestParam(value = "appId") Long appId) {
        List<TutorialCategoryDTO> categoryMsts = new ArrayList<>();
        try {
            categoryMsts.addAll(tutorialService.categoryWithTutorialList(appId));
            return ResponseEntity.ok(categoryMsts);
        } catch (Exception e) {
            log.error("TutorialController : categoryWithTutorialList : " + e.getMessage(), e);
        }
        return ResponseEntity.ok(categoryMsts);
    }

    @GetMapping("/getTutorialMstById")
    public ResponseEntity<TutorialMstDTO> getTutorialMstById(@RequestParam(value = "mstId") Long mstId) {
        return ResponseEntity.ok(mapperService.tutorialMstToDTO(tutorialMstRepository.findById(mstId).get()));
    }

    @PostMapping("/saveTutorialMst")
    public ResponseEntity<?> saveTutorialMst(@Valid @ModelAttribute TutorialMstFormDTO formVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            errorDTOS = tutorialService.saveTutorialMst(formVM);
            if (errorDTOS.isEmpty()) {
                return ResponseEntity.created(URI.create("/saveTutorialMst")).build();
            }

            return ResponseEntity.badRequest().body(errorDTOS);
        } catch (Exception e) {
            log.error("TutorialController : saveTutorialMst : " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error on adding tutorial mst"));
        }
        return ResponseEntity.badRequest().body(errorDTOS);
    }

    @GetMapping("/getAllTutorialDtlsByMstId")
    public ResponseEntity<List<TutorialDtlDTO>> getAllTutorialDtlsByMstId(@RequestParam(value = "mstId") Long mstId) {
        return ResponseEntity.ok(tutorialService.getAllTutorialDtlsByMstId(mstId));
    }
}
