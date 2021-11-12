package com.ekinsol.challenge.apiservice.controller;

import com.ekinsol.challenge.apiservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ekinsol/api/files")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping(value= "/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value= "file") final MultipartFile multipartFile) {
                fileService.uploadFile(multipartFile);
                final String response = "[" + multipartFile.getOriginalFilename() + "] uploaded successfully.";
                return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
