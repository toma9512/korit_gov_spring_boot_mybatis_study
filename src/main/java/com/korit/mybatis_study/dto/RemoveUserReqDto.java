package com.korit.mybatis_study.dto;

import com.korit.mybatis_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveUserReqDto {
    private String username;
    private String password;
}
