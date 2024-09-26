package com.code.review.domain.board.dto;

import com.code.review.domain.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private final Long id;
    private final String title;
    private final String content;

    /**
     * 정적 팩토리 메서드 사용
     * 참고 블로그
     * https://tecoble.techcourse.co.kr/post/2020-05-26-static-factory-method/
     */
    public static BoardResponseDto of(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent());
    }
    private BoardResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
