package com.servme.tes.todoapp.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
