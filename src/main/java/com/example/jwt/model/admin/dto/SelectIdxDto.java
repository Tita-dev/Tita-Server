package com.example.jwt.model.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class SelectIdxDto {

    public String Role;

    public List<Long> userIdx;
}
