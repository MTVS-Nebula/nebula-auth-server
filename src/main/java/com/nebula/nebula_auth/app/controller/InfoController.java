package com.nebula.nebula_auth.app.controller;

import com.nebula.nebula_auth.app.dto.info.InfoDTO;
import com.nebula.nebula_auth.app.service.InfoService;
import com.nebula.nebula_auth.helper.api.ResponseMessage;
import com.nebula.nebula_auth.helper.api.ResultResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("info")
public class InfoController {

    private final InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public ResponseEntity<?> getInfo(){
        String username = getContextUsername();
        if(username.equals("anonymousUser")){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.FORBIDDEN.value(),
                            "context holder값이 유효하지 않습니다.")); }
        InfoDTO infoDTO = infoService.getUserInfo(username);

        Map<String , Object> resultMap = new HashMap<>();
        resultMap.put("info", infoDTO);

        return ResponseEntity.ok().body(new ResultResponseMessage(
           HttpStatus.OK.value(), "success", resultMap
        ));
    }

    private String getContextUsername(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return username;
        } catch (Exception e) {
            return null;
        }
    }
}
