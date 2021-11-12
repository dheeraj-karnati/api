package com.ekinsol.challenge.apiservice.models;


public class EmailRequest {

    public String recepientEmail;

    public String subject;

    public String body;

    public String getRecepientEmail() {
        return recepientEmail;
    }

    public void setRecepientEmail(String recepientEmail) {
        this.recepientEmail = recepientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
