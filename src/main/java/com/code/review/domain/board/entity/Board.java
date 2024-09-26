package com.code.review.domain.board.entity;

import com.code.review.domain.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
//    @OneToMany(mappedBy = "comment")
//    private List<Comment> commentList;

    /**
     * 정적 팩토리 메서드 사용
     * 참고 블로그
     * https://taehoung0102.tistory.com/223
     */
    public static Board of(BoardRequestDto boardRequestDto) {
        Board board = new Board();
        board.title = boardRequestDto.getTitle();
        board.content = boardRequestDto.getContent();
        return board;
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }
}
