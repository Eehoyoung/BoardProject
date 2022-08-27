package com.example.test.dto;

import com.example.test.model.Board;
import lombok.Data;

@Data
public class BoardSaveRequestDto {

    private String title;
    private String content;

    public static Board toEntity(BoardSaveRequestDto dto){
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
