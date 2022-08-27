package com.example.test.controller;

import com.example.test.dto.BoardSaveRequestDto;
import com.example.test.model.Board;
import com.example.test.repository.BoardRepository;
import com.example.test.service.BoardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    public BoardController(BoardService boardService, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
    }

    @ApiOperation(value = "게시물 작성")
    @PostMapping("/createpost")
    @ResponseBody
    public String savePost(@RequestBody BoardSaveRequestDto dto){
        boardService.savePost(dto);
        return "ok";
    }

    @GetMapping("/post/page")
    public Page<Board> contentList(@PageableDefault(size = 3, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @ApiOperation(value = "게시물 조회")
    @GetMapping("/post/{id}")
    public Board BoardDetail(@PathVariable int id,Board board){
        board.setCount(board.getCount()+1);
        return boardRepository.findById(id).orElseThrow(()->
                new RuntimeException("게시물이 존재하지 않습니다."));
    }

    @ApiOperation(value = "게시물 삭제")
    @DeleteMapping("/post/{id}")
    public String deletPost(@PathVariable int id){
        try {
            boardRepository.deleteById(id);
        }catch (Exception e){
            return "해당 게시물은 존재하지 않습니다";
        }
        return "게시물이 삭제되었습니다.";
    }

    @ApiOperation(value = "게시물 수정")
    @Transactional
    @PutMapping("/updatepost/{id}")
    public Board updatePost(@PathVariable int id, @RequestBody Board reqBoard){
        Board board = boardRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 게시물이 존재하지 않습니다."));

        board.setTitle(reqBoard.getTitle());
        board.setContent(reqBoard.getContent());

        return board;
    }
}
