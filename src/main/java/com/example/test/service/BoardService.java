package com.example.test.service;

import com.example.test.dto.BoardSaveRequestDto;
import com.example.test.model.Board;
import com.example.test.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void savePost(BoardSaveRequestDto dto){
        Board boardEntity = BoardSaveRequestDto.toEntity(dto);
        boardRepository.save(boardEntity);
    }
}
