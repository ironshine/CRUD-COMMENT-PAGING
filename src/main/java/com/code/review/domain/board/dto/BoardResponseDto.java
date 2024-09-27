package com.code.review.domain.board.dto;

import com.code.review.domain.board.entity.Board;
import com.code.review.domain.comment.dto.CommentResponseDto;
import com.code.review.domain.comment.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final List<CommentResponseDto> commentList;

    /**
     * 정적 팩토리 메서드 사용
     * 참고 블로그
     * https://tecoble.techcourse.co.kr/post/2020-05-26-static-factory-method/
     */
    public static BoardResponseDto of(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent(), board.getCommentList());
    }

    private BoardResponseDto(Long id, String title, String content, List<Comment> commentList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentList = commentList.stream()
                .map(comment ->
                        CommentResponseDto.builder()
                                .id(comment.getId())
                                .boardId(comment.getBoard().getId())
                                .content(comment.getContent())
                                .build())
                .toList();
    }
}
