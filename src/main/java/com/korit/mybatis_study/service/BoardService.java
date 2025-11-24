package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.AddBoardReqDto;
import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.EditBoardReqDto;
import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public ApiRespDto<?> addBoard(AddBoardReqDto addBoardReqDto) {
        if (boardRepository.findBoardByTitle(addBoardReqDto.getTitle()).isPresent()) {
            return new ApiRespDto<>("failed", "중복된 title", null);
        }

        Optional<Board> board = boardRepository.addBoard(addBoardReqDto.toEntity());
        if (board.isPresent()) {
            return new ApiRespDto<>("success", "게시글 추가 완료", board.get());
        }

        return new ApiRespDto<>("failed", "게시물 추가 실패", null);
    }

    public ApiRespDto<?> getBoardAll() {
        return new ApiRespDto<>("success", "전체 조회", boardRepository.getBoardAll());
    }

    public ApiRespDto<?> getBoardByBoardId(Integer boardId) {
        Optional<Board> foundBoard = boardRepository.findBoardByBoardId(boardId);
        if (foundBoard.isPresent()) {
            return new ApiRespDto<>("success", "단건 조회", foundBoard.get());
        }
        return new ApiRespDto<>("failed", "찾으려는 id가 존재하지 않습니다.", null);
    }

    public ApiRespDto<?> editBoard(EditBoardReqDto editBoardReqDto) {
        Optional<Board> foundBoard = boardRepository.findBoardByBoardId(editBoardReqDto.getBoardId());
        if (foundBoard.isEmpty()) {
            return new ApiRespDto<>("failed", "id가 존재하지 않습니다.",null);
        }

        int result = boardRepository.editBoard(editBoardReqDto.toEntity());

        if (result != 1) {
            return new ApiRespDto<>("failed", "수정 실패", null);
        }
        return new ApiRespDto<>("success", "수정 완료", null);
    }

    public ApiRespDto<?> removeBoard(Integer boardId) {
        Optional<Board> foundBoard = boardRepository.findBoardByBoardId(boardId);
        if (foundBoard.isEmpty()) {
            return new ApiRespDto<>("failed", "id가 존재하지 않습니다.",null);
        }

        int result = boardRepository.removeBoard(boardId);

        if (result != 1) {
            return new ApiRespDto<>("failed", "삭제 실패", null);
        }
        return new ApiRespDto<>("success", "삭제 완료", null);
    }
}
