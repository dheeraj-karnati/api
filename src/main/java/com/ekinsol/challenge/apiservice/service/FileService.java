package com.ekinsol.challenge.apiservice.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class FileService {

    @Autowired
    AmazonS3 amazonS3;

    @Value("${ekinsol.s3.bucketName}")
    private String s3BucketName;

    public String generatePresignedUrl(String s3BucketName, String objectKey) {

        URL url = null;
        try {
            AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                    .withRegion("us-east-1")
                    .withCredentials(new ProfileCredentialsProvider()).build();

            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = Instant.now().toEpochMilli();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(s3BucketName, objectKey)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);


        } catch (AmazonServiceException e) {

            e.printStackTrace();
        } catch (SdkClientException e) {

            e.printStackTrace();
        }
        return url.toString();
    }

    @Async
    public void uploadFile(final MultipartFile multipartFile) {
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            uploadFileToS3Bucket(s3BucketName, file);
            file.delete();
        } catch (final AmazonServiceException ex) {
        }
    }

    private File convertMultiPartFileToFile(MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            System.out.println("exception in converting file"+ex);
        }
        return file;

    }

    private void uploadFileToS3Bucket(final String bucketName, final File file) {
        final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
        amazonS3.putObject(putObjectRequest);
    }


}
