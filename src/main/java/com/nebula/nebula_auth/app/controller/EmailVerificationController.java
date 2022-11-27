package com.nebula.nebula_auth.app.controller;

import com.nebula.nebula_auth.app.service.EmailVerificationService;
import com.nebula.nebula_auth.helper.api.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("verification")
public class EmailVerificationController {
    private final EmailVerificationService emailVerificationService;
    @Autowired
    public EmailVerificationController(EmailVerificationService emailVerificationService) {
        this.emailVerificationService = emailVerificationService;
    }
    @PostMapping("email/{email}")
    public ResponseEntity<?> sendVerificationCode(@PathVariable String email){
        try {
            emailVerificationService.sendVerificationEmail(email);
            return ResponseEntity
                    .created(URI.create("email/verification"))
                    .body(new ResponseMessage(HttpStatus.OK.value(),"success"));
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
