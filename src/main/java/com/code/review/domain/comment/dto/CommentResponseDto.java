package com.code.review.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {
    private final Long id;
    private final Long boardId;
    private final String content;
}
