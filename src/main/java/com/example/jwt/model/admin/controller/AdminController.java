package com.example.jwt.model.admin.controller;

import com.example.jwt.model.admin.dto.SelectIdxDto;
import com.example.jwt.model.admin.service.AdminService;
import com.example.jwt.response.ResponseService;
import com.example.jwt.response.result.CommonResult;
import com.example.jwt.response.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tita/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ResponseService responseService;
    private final AdminService adminService;

    @PostMapping("/grant")
    public CommonResult grantAdmin(@RequestBody String code) throws Exception {
        adminService.grantAdmin(code);
        return responseService.getSuccessResult();
    }

    @GetMapping("/authorization")
    public SingleResult<List<Map<String,String>>> authorizationManagerList(){
        return responseService.getSingleResult(adminService.authorizationManagerList());
    }

    @PostMapping("/authorization/select")
    public CommonResult authorizationManager(@RequestBody SelectIdxDto selectIdxDto){
        adminService.authorizationManager(selectIdxDto);
        return responseService.getSuccessResult();
    }
}
