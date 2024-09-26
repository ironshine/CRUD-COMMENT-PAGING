package com.code.review.domain.board.service;

import com.code.review.domain.board.dto.BoardRequestDto;
import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.board.entity.Board;
import com.code.review.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
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

    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();
//        List<BoardResponseDto> dtoList = new ArrayList<>();
//        for (Board board : boardList) {                        // .stream()
//            BoardResponseDto dto = BoardResponseDto.of(board); // .map()
//            dtoList.add(dto                                    // .toList()
//        }
        return boardList.stream()
                .map(BoardResponseDto::of)
                .toList();
        /**
         * .map(board -> BoardResponseDto.of(board)) 와 동일합니다
         * List<Board> -> List<BoardResponseDto> 로 변경해줍니다.
         * List<Board>의 board를 순차적으로 가져와 BoardResponseDto.of(board)로 dto를 생성합니다.
         * 생성된 dto는 아래의 .toList()에 의해 List<BoardResponseDto>에 담깁니다.
         */
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
