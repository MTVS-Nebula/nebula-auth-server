package com.nebula.nebula_auth.app.controller;

import com.nebula.nebula_auth.app.dto.duplication.EmailDTO;
import com.nebula.nebula_auth.app.dto.duplication.UsernameDTO;
import com.nebula.nebula_auth.app.service.DupCheckService;
import com.nebula.nebula_auth.helper.api.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("duplication")
public class DupCheckController {
    private final DupCheckService dupCheckService;

    @Autowired
    public DupCheckController(DupCheckService dupCheckService) {
        this.dupCheckService = dupCheckService;
    }


    @PostMapping("username")
    public ResponseEntity<?> getUsernameDuplicationCheck(@Valid @RequestBody UsernameDTO usernameDTO){
        boolean result = dupCheckService.checkUsername(usernameDTO.getUsername());

        if(result){
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(HttpStatus.OK.value(),"username is available"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "username is not available"));
        }
    }

    @PostMapping("email")
    public ResponseEntity<?> getEmailDuplicationCheck(@Valid @RequestBody EmailDTO emailDTO){
        boolean result = dupCheckService.checkEmail(emailDTO.getEmail());

        if(result){
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(HttpStatus.OK.value(),"email is available"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "email is not available"));
        }
    }
}
