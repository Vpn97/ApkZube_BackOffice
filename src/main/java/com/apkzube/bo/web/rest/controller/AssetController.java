package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.service.FileSystemService;
import java.io.IOException;
import javax.activation.FileTypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    private Logger log = LoggerFactory.getLogger(AssetController.class);

    @Autowired
    private FileSystemService fileSystemService;

    @PostMapping(value = "/uploadImage", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@RequestParam(value = "img") MultipartFile image) {
        try {
            String filePath = fileSystemService.saveImage(image.getBytes(), image.getOriginalFilename());
            return ResponseEntity.ok(filePath);
        } catch (Exception e) {
            log.error("AppMstController : findAppMstById : " + e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/image/{name}")
    public ResponseEntity<byte[]> getImageByName(@PathVariable(value = "name") String name) throws IOException {
        try {
            FileSystemResource file = fileSystemService.getImageByName(name);

            return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file.getFile())))
                .body(file.getInputStream().readAllBytes());
        } catch (Exception e) {
            log.error("ImageController : getImageByName : " + e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/icon/{name}")
    public ResponseEntity<byte[]> getIconByName(@PathVariable(value = "name") String name) throws IOException {
        try {
            FileSystemResource file = fileSystemService.getIconByName(name);

            return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file.getFile())))
                .body(file.getInputStream().readAllBytes());
        } catch (Exception e) {
            log.error("ImageController : getIconByName : " + e.getMessage(), e);
            throw e;
        }
    }
}
