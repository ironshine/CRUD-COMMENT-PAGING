package com.code.review.domain.comment.service;

import com.code.review.domain.board.entity.Board;
import com.code.review.domain.board.service.BoardService;
import com.code.review.domain.comment.dto.CommentRequestDto;
import com.code.review.domain.comment.dto.CommentResponseDto;
import com.code.review.domain.comment.entity.Comment;
import com.code.review.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardService boardService;

    @Transactional
    public CommentResponseDto addComment(Long boardId, CommentRequestDto commentRequestDto) {
        Board board = boardService.findById(boardId);
        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .board(board)
                .build();
        commentRepository.save(comment);
        return CommentResponseDto.builder()
                .id(comment.getId())
                .boardId(board.getId())
                .content(comment.getContent())
                .build();
    }

    public List<CommentResponseDto> getComments(Long boardId) {
        Board board = boardService.findById(boardId);
        return commentRepository.findAllByBoard(board).stream()
                .map(comment -> CommentResponseDto.builder()
                        .id(comment.getId())
                        .boardId(comment.getBoard().getId())
                        .content(comment.getContent())
                        .build())
                .toList();
    }

    public CommentResponseDto getComment(Long boardId, Long commentId) {
        Board board = boardService.findById(boardId);
        Comment comment = commentRepository.findByIdJoinBoardWhereCommentIdAndBoard(board, commentId);
        return CommentResponseDto.builder()
                .id(comment.getId())
                .boardId(comment.getBoard().getId())
                .content(comment.getContent())
                .build();
    }

    @Transactional
    public CommentResponseDto updateComment(Long boardId, Long commentId, CommentRequestDto commentRequestDto) {
        boardService.findById(boardId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("없는 ID입니다."));
        comment.update(commentRequestDto);
        return CommentResponseDto.builder()
                .id(comment.getId())
                .boardId(comment.getBoard().getId())
                .content(comment.getContent())
                .build();
    }

    @Transactional
    public String deleteComment(Long boardId, Long commentId) {
        boardService.findById(boardId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("없는 ID입니다."));
        commentRepository.delete(comment);
        return "댓글이 삭제 되었습니다.";
    }
}
