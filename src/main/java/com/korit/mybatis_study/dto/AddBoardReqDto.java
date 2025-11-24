package com.korit.mybatis_study.dto;

import com.korit.mybatis_study.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBoardReqDto {
    private String title;
    private String content;
    private String username;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .username(username)
                .build();
    }
}
