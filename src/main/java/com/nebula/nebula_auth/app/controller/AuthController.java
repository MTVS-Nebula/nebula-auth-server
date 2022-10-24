package com.nebula.nebula_auth.app.controller;

import com.nebula.nebula_auth.app.dto.SignUpDTO;
import com.nebula.nebula_auth.app.service.AuthService;
import com.nebula.nebula_auth.helper.api.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDTO signUpDTO){
        boolean result = authService.signUp(signUpDTO);
        if(result){
            return ResponseEntity
                    .created(URI.create("/users/"+signUpDTO.getUsername()))
                    .body(new ResponseMessage(HttpStatus.CREATED.value(),"success signup"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "fail signup (create user)"));
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> signUpValidExceptionHandler(MethodArgumentNotValidException e){
        return ResponseEntity
                .badRequest()
                .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "fail signup (validation)"));
    }

}
