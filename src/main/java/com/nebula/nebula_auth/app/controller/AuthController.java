package com.nebula.nebula_auth.app.controller;

import com.nebula.nebula_auth.app.dto.auth.LoginDTO;
import com.nebula.nebula_auth.app.dto.auth.SignUpDTO;
import com.nebula.nebula_auth.app.service.AuthService;
import com.nebula.nebula_auth.helper.api.ResponseMessage;
import com.nebula.nebula_auth.helper.api.ResultResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
                    .body(new ResponseMessage(HttpStatus.CREATED.value(),"signup success"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "fail signup"));
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO){
        String token = null;

        try {
            token = authService.login(loginDTO);
        } catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "username/password is not valid"));
        }

        if(token != null){
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("token", token);
            return ResponseEntity
                    .ok()
                    .body(new ResultResponseMessage(HttpStatus.OK.value(), "login success", resultMap));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "fail login"));
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> signUpValidExceptionHandler(MethodArgumentNotValidException e){
        return ResponseEntity
                .badRequest()
                .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "fail validation"));
    }

}
