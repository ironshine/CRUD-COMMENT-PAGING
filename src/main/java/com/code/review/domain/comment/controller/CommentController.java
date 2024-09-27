package com.code.review.domain.comment.controller;

import com.code.review.domain.comment.dto.CommentRequestDto;
import com.code.review.domain.comment.dto.CommentResponseDto;
import com.code.review.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> addComment(
            @PathVariable Long boardId,
            @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.addComment(boardId, commentRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(
            @PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getComments(boardId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(boardId, commentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.updateComment(boardId, commentId, commentRequestDto));
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(boardId, commentId));
    }
}
