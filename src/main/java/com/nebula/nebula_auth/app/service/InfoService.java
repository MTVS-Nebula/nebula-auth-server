package com.nebula.nebula_auth.app.service;

import com.nebula.nebula_auth.app.dto.info.InfoDTO;

public interface InfoService {
    InfoDTO getUserInfo(String username);
}
