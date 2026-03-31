package com.example.NebulaByte.Email_Writer.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NebulaByte.Email_Writer.Dto.EmailRequest;
import com.example.NebulaByte.Email_Writer.Service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmailReply(@RequestBody EmailRequest request) {

        String response = emailService.generateEmail(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/test")
    public String test() {
        return "API WORKING";
    }

}
