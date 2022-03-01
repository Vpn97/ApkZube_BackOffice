package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.service.CompilerService;
import com.apkzube.bo.service.dto.CompilerFormDTO;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compiler")
public class CompilerController {

    private Logger log = LoggerFactory.getLogger(CompilerController.class);

    @Autowired
    private CompilerService compilerService;

    @PostMapping("/python3")
    public ResponseEntity<?> runPythonCode(@Valid @RequestBody CompilerFormDTO compilerFormDTO) {
        try {
            return ResponseEntity.ok(compilerService.runPythonCode(compilerFormDTO));
        } catch (Exception e) {
            log.error("CompilerController : python3 : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
