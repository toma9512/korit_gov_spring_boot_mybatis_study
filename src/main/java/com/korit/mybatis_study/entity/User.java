package com.korit.mybatis_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
