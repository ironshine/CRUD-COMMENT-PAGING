package com.code.review.domain.board.controller;

import com.code.review.domain.board.dto.BoardRequestDto;
import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> addBoard(
            @RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.ok(boardService.addBoard(boardRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoards() {
        return ResponseEntity.ok(boardService.getBoards());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard(
            @PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.getBoard(boardId));
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.ok(boardService.updateBoard(boardId, boardRequestDto));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(
            @PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.deleteBoard(boardId));
    }
}
