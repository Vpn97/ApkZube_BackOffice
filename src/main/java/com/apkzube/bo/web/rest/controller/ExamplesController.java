package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.service.ExamplesService;
import com.apkzube.bo.service.dto.ErrorDTO;
import com.apkzube.bo.service.dto.PracticeExampleFormDTO;
import com.apkzube.bo.service.dto.PracticeProgramDTO;
import com.apkzube.bo.service.dto.ProgramCategoryDTO;
import com.apkzube.bo.service.dto.ProgramCategoryFormDTO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/examples")
public class ExamplesController {

    private Logger log = LoggerFactory.getLogger(ExamplesController.class);

    @Autowired
    ExamplesService examplesService;

    @GetMapping("/categories")
    public ResponseEntity<List<ProgramCategoryDTO>> categories(@Valid @NotNull @RequestParam(value = "appId") Long appId) {
        return ResponseEntity.ok(examplesService.getProgramCategories(appId));
    }

    @GetMapping("/category")
    public ResponseEntity<ProgramCategoryDTO> category(@Valid @NotNull @RequestParam(value = "catId") Long catId) {
        return ResponseEntity.ok(examplesService.getProgramCategory(catId));
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@Valid @ModelAttribute ProgramCategoryFormDTO catVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            errorDTOS = examplesService.saveOrUpdateCategory(catVM, false);
            if (errorDTOS.isEmpty()) {
                return ResponseEntity.created(URI.create("/addCategory")).build();
            }

            return ResponseEntity.badRequest().body(errorDTOS);
        } catch (Exception e) {
            log.error("ExamplesController : addCategory : " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error on adding program category"));
        }
        return ResponseEntity.badRequest().body(errorDTOS);
    }

    @PostMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@Valid @ModelAttribute ProgramCategoryFormDTO catVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            errorDTOS = examplesService.saveOrUpdateCategory(catVM, true);
            if (errorDTOS.isEmpty()) {
                return ResponseEntity.created(URI.create("/updateCategory")).build();
            }

            return ResponseEntity.badRequest().body(errorDTOS);
        } catch (Exception e) {
            log.error("ExamplesController : updateCategory : " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error on updating program category"));
        }
        return ResponseEntity.badRequest().body(errorDTOS);
    }

    @GetMapping("/getExamplesByCategory")
    public ResponseEntity<List<PracticeProgramDTO>> getExamplesByCategory(@Valid @NotNull @RequestParam(value = "catId") Long catId) {
        return ResponseEntity.ok(examplesService.getExamplesByCategory(catId));
    }

    @GetMapping("/programById")
    public ResponseEntity<PracticeProgramDTO> programById(@Valid @NotNull @RequestParam(value = "programId") Long programId) {
        return ResponseEntity.ok(examplesService.programById(programId));
    }

    @PostMapping("/saveProgram")
    public ResponseEntity<?> saveProgramExample(@Valid @RequestBody PracticeExampleFormDTO formVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            errorDTOS = examplesService.saveOrUpdateProgramExample(formVM);
            if (errorDTOS.isEmpty()) {
                return ResponseEntity.created(URI.create("/saveProgram")).build();
            }

            return ResponseEntity.badRequest().body(errorDTOS);
        } catch (Exception e) {
            log.error("ExamplesController : saveProgram : " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error on saving program"));
        }
        return ResponseEntity.badRequest().body(errorDTOS);
    }
}
