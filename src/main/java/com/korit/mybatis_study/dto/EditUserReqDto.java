package com.korit.mybatis_study.dto;

import com.korit.mybatis_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditUserReqDto {
    private String username;
    private String password;
    private String newPassword;
    private String email;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(newPassword)
                .email(email)
                .build();
    }
}
