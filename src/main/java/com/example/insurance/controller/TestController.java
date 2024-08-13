package com.example.insurance.controller;

import com.example.insurance.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-reminders")
    public ResponseEntity<String> sendReminders() {
        emailService.sendPaymentReminderEmails();
        return ResponseEntity.ok("Reminders sent.");
    }
}


