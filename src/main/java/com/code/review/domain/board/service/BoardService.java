package com.code.review.domain.board.service;

import com.code.review.domain.board.dto.BoardRequestDto;
import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.board.entity.Board;
import com.code.review.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto addBoard(BoardRequestDto boardRequestDto) {
        Board board = Board.of(boardRequestDto);
        boardRepository.save(board);
        return BoardResponseDto.of(board);
    }

    @Transactional(readOnly = true)
    public Page<BoardResponseDto> getBoards(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Board> boardList = boardRepository.findAll(pageable);

        return boardList.map(BoardResponseDto::of);
    }

    public BoardResponseDto getBoard(Long boardId) {
        Board board = findById(boardId);
        return BoardResponseDto.of(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = findById(boardId);
        board.update(boardRequestDto);
        return BoardResponseDto.of(board);
    }

    @Transactional
    public String deleteBoard(Long boardId) {
        Board board = findById(boardId);
        boardRepository.delete(board);
        return "게시물을 삭제하였습니다.";
    }

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new NullPointerException("boardId에 해당하는 Board가 없습니다."));
    }
}
