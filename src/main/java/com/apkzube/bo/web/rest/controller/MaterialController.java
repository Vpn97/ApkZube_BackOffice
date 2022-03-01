package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.entity.AppMaterialType;
import com.apkzube.bo.repository.AppMaterialMstRepository;
import com.apkzube.bo.repository.AppMaterialTypeRepository;
import com.apkzube.bo.service.MaterialService;
import com.apkzube.bo.service.dto.AppMaterialFormDTO;
import com.apkzube.bo.service.dto.AppMaterialMstDTO;
import com.apkzube.bo.service.dto.ErrorDTO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private Logger log = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    private AppMaterialMstRepository appMaterialMstRepository;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private AppMaterialTypeRepository appMaterialTypeRepository;

    @GetMapping("/findAllByAppId")
    public ResponseEntity<List<AppMaterialMstDTO>> findAllByAppId(@Valid @NotNull @RequestParam(value = "appId") Long appId) {
        List<AppMaterialMstDTO> appMaterialMsts = materialService.findAllByAppId(appId);
        return ResponseEntity.ok(appMaterialMsts);
    }

    @GetMapping("/findByMatId")
    public ResponseEntity<AppMaterialMstDTO> findByMatId(@Valid @NotNull @RequestParam(value = "matId") Long matId) {
        Optional<AppMaterialMstDTO> materialMst = materialService.findById(matId);
        return materialMst.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/addMaterial")
    public ResponseEntity<?> addMaterial(@Valid @ModelAttribute AppMaterialFormDTO materialVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            errorDTOS = materialService.addMaterial(materialVM);
            if (errorDTOS.isEmpty()) {
                return ResponseEntity.created(URI.create("/addMaterial")).build();
            }

            return ResponseEntity.badRequest().body(errorDTOS);
        } catch (Exception e) {
            log.error("MaterialController : addMaterial : " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error on adding material"));
        }
        return ResponseEntity.badRequest().body(errorDTOS);
    }

    @PostMapping("/updateMaterial")
    public ResponseEntity<?> updateMaterial(@Valid @ModelAttribute AppMaterialFormDTO materialVM) {
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        try {
            errorDTOS = materialService.updateMaterial(materialVM);
            if (errorDTOS.isEmpty()) {
                return ResponseEntity.created(URI.create("/updateMaterial")).build();
            }

            return ResponseEntity.badRequest().body(errorDTOS);
        } catch (Exception e) {
            log.error("MaterialController : updateMaterial : " + e.getMessage(), e);
            errorDTOS.add(new ErrorDTO("Error on updating material"));
        }
        return ResponseEntity.badRequest().body(errorDTOS);
    }

    @GetMapping("/fetchMaterialTypeCodes")
    public ResponseEntity<List<AppMaterialType>> fetchMaterialTypeCodes() {
        try {
            return ResponseEntity.ok(appMaterialTypeRepository.findAll());
        } catch (Exception e) {
            log.error("MaterialController : fetchMaterialTypeCodes : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
