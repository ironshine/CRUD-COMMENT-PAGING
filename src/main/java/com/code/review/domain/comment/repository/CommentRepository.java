package com.code.review.domain.comment.repository;

import com.code.review.domain.board.entity.Board;
import com.code.review.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoard(Board board);
//    @Query("SELECT c FROM Comment c JOIN Board b ON b.id = c.board.id WHERE c.id = commentId")
//    Comment findByIdJoinBoard(Board board, Long commentId);
    @Query("SELECT c FROM Comment c JOIN c.board b WHERE c.id = :commentId AND b = :board")
    Comment findByIdJoinBoardWhereCommentIdAndBoard(
            @Param("board") Board board,
            @Param("commentId") Long commentId);
}
