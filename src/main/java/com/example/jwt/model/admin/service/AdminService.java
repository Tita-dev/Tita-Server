package com.example.jwt.model.admin.service;

import com.example.jwt.model.admin.dto.SelectIdxDto;

import java.util.List;
import java.util.Map;

public interface AdminService {
    void grantAdmin (String code) throws Exception;

    List<Map<String,String>> authorizationManagerList();

    void authorizationManager (SelectIdxDto selectIdxDto);
}
