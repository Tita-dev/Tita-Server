package com.example.jwt.model.user.enum_type;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_NOT_STUDENT, ROLE_PASSWORD_CHANGE, ROLE_STUDENT, ROLE_DORMITORY_MANAGER,ROLE_SCHOOL_MANAGER, ROLE_SCHOOL_ADMIN
}