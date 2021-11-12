package com.ekinsol.challenge.apiservice.controller;


import com.ekinsol.challenge.apiservice.models.EmailRequest;
import com.ekinsol.challenge.apiservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/ekinsol/api/email")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        String recipient = emailRequest.recepientEmail;
        String subject = emailRequest.subject;
        String content = emailRequest.body;

        try {
            emailService.sendEmail(recipient, subject, content);
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println(e.getStackTrace());
        }
        return new ResponseEntity<>("Email Sent Succesfully", HttpStatus.OK);

    }


}
