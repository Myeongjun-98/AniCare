package com.AniCare.demo.service.community;

import com.AniCare.demo.Dto.community.BoardListMainDto;
import com.AniCare.demo.Dto.community.BoardListSubDto;
import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.entity.community.ErrandBoard;
import com.AniCare.demo.entity.community.MeetingBoard;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.community.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class
CommunityMainService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final MeetingBoardRepository meetingBoardRepository;
    private final ErrandBoardRepository errandBoardRepository;

    private final UserRepository userRepository;

    // ================ 커뮤니티 메인 페이지-게시글 목록 불러오기 ================
    public List<BoardListMainDto> getBoardMainList() {
        List<BoardListMainDto> boardListMainDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByBoardHitDesc();

        for (Board board : boards) {
            BoardListMainDto boardListMainDto = BoardListMainDto.to(board);
            boardListMainDtos.add(boardListMainDto);

            //게시글 카테고리 가져오기 (페이지에 표시해줄 것)
            if (board.getBoardType().name() == "MEETING") {
                MeetingBoard mCategory = meetingBoardRepository.findByBoardId(board.getId());

                boardListMainDto.setCategory(mCategory.getMeetingCategory().toString());

            } else if (board.getBoardType().name() == "ERRAND") {
                ErrandBoard eCategory = errandBoardRepository.findByBoardId(board.getId());

                boardListMainDto.setCategory(eCategory.getErrandCategory().toString());
            }

        }

        return boardListMainDtos;
    }

    // ================ 커뮤니티 검색결과 페이지-게시글 목록 불러오기 ================
    public List<BoardListSubDto> getBoardSearchList() {
        List<BoardListSubDto> boardListSubDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByIdDesc();

        for (Board board : boards) {
            int likeCount = boardLikeRepository.countByBoardId(board.getId());
            BoardListSubDto boardListSubDto = BoardListSubDto.to(board, likeCount);
            boardListSubDtos.add(boardListSubDto);


        }

        return boardListSubDtos;
    }


}
