package com.example.jwt.model.admin.service;

import com.example.jwt.model.admin.dto.SelectIdxDto;
import com.example.jwt.model.user.User;
import com.example.jwt.model.user.enum_type.UserRole;
import com.example.jwt.model.user.repository.UserRepository;
import com.example.jwt.model.user.service.UserService;
import com.example.jwt.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminServiceMpl implements AdminService{

    private final UserService userService;
    private final UserRepository userRepository;
    private final CurrentUserUtil currentUserUtil;

    @Value("${tita.admin-code}")
    private String adminCode;

    @Override
    public void grantAdmin(String code) throws Exception{
        if (code.equals(adminCode))
            userService.modifyUserRole(currentUserUtil.getCurrentUser(), UserRole.ROLE_SCHOOL_ADMIN);
        else
            throw new Exception();
    }

    @Override
    public List<Map<String, String>> authorizationManagerList() {
        List<User> users = userRepository.findAll();
        List<Map<String,String>> userList = new ArrayList<>();

        for (User user : users){
            Map<String,String> map = new HashMap<>();
            map.put("UserIdx", user.getUserIdx().toString());
            map.put("UserName", user.getName());
            map.put("UserRole", user.getRole().toString());
            userList.add(map);
        }
        return userList;
    }

    @Override
    public void authorizationManager(SelectIdxDto selectIdxDto) {
        if (selectIdxDto.Role.equals("school")){
            for (Long i : selectIdxDto.getUserIdx()){
                userService.modifyUserRole(userRepository.findByUserIdx(i),UserRole.ROLE_SCHOOL_MANAGER);
            }
        }else{
            for (Long i : selectIdxDto.getUserIdx()){
                userService.modifyUserRole(userRepository.findByUserIdx(i),UserRole.ROLE_DORMITORY_MANAGER);
            }
        }
    }
}
