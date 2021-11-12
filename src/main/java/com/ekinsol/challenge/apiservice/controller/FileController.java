package com.ekinsol.challenge.apiservice.controller;

import com.ekinsol.challenge.apiservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${ekinsol.s3.bucketName}")
    private String s3BucketName;

    @Value("${ekinsol.s3.objectKey}")
    private String objectKey;

    private static final String FILE_MIME_TYPE_KEY = "fileMimeType";

    private static final String FILE_MIME_TYPE_EXCEL = "application/vnd.ms-excel";

    @GetMapping("/presignedUrl")
    public ResponseEntity<String> getPresignedUrl() {

        return new ResponseEntity<>(fileService.generatePresignedUrl(s3BucketName,objectKey), HttpStatus.OK);

    }

    @PostMapping(value= "/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value= "file") final MultipartFile multipartFile) {
                fileService.uploadFile(multipartFile);
                final String response = "[" + multipartFile.getOriginalFilename() + "] uploaded successfully.";
                return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
