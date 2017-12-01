package com.nagesh.api_assignment.controller;

import com.nagesh.api_assignment.exception.StorageFileNotFoundException;
import com.nagesh.api_assignment.model.MetaData;
import com.nagesh.api_assignment.service.MetaDataService;
import com.nagesh.api_assignment.service.StorageProperties;
import com.nagesh.api_assignment.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/v1/files")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    private final StorageService storageService;

    private final MetaDataService metaDataService;

    private final StorageProperties storageProperties;

    @Autowired
    public FileUploadController(final StorageService storageService, final MetaDataService metaDataService, final StorageProperties storageProperties) {
        this.storageService = storageService;
        this.metaDataService = metaDataService;
        this.storageProperties = storageProperties;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {

        LOGGER.info(file.getOriginalFilename());
        storageService.store(file);
        metaDataService.saveMetaData(new MetaData(file.getOriginalFilename(), storageProperties.getLocation().concat("/").concat(file.getOriginalFilename()), file.getContentType()));
        return new ResponseEntity<String>(file.getName(), HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
