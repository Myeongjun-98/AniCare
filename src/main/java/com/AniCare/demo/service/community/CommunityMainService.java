package com.AniCare.demo.service.community;

import com.AniCare.demo.Dto.community.BoardListMainDto;
import com.AniCare.demo.Dto.community.BoardListSubDto;
import com.AniCare.demo.Dto.community.BoardSearchForm;
import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.entity.community.BoardFile;
import com.AniCare.demo.entity.community.ErrandBoard;
import com.AniCare.demo.entity.community.MeetingBoard;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.community.*;
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
    public List<BoardListMainDto> getBoardMainList(){
        List<BoardListMainDto> boardListMainDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByLikeCountDesc();

        for(Board board : boards){
            BoardListMainDto boardListMainDto = BoardListMainDto.to(board);
            boardListMainDtos.add(boardListMainDto);

            //게시글 썸네일 이미지 가져오기
            BoardFile boardFile = boardFileRepository.findByBoardIdAndThumbnailYn(board.getId(),"Y");

            //!!: 첨부파일이 없는 게시글에는 기본 이미지 세팅해주기
            if (boardFile != null) {
                boardListMainDto.setFileUrl(boardFile.getFileUrl());
            } else {
                boardListMainDto.setFileUrl("/anicareFile/default-thumbnail.png");
            }

            //게시글 카테고리 가져오기 (페이지에 표시해줄 것)
            if(board.getBoardType().name().equals("MEETING")) {
                MeetingBoard mCategory = meetingBoardRepository.findByBoardId(board.getId());

                boardListMainDto.setCategory(mCategory.getMeetingCategory().toString());

            } else if(board.getBoardType().name().equals("ERRAND")){
                ErrandBoard eCategory = errandBoardRepository.findByBoardId(board.getId());

                boardListMainDto.setCategory(eCategory.getErrandCategory().toString());
            }

        }

        return boardListMainDtos;
    }

    // ================ 커뮤니티 검색결과 페이지-게시글 목록 불러오기 ================
    public List<BoardListMainDto> getBoardSearchList(BoardSearchForm boardSearchForm){
        List<BoardListMainDto> boardListMainDtos = new ArrayList<>();

        List<Board> boards = boardRepository.searchByBoardTypeAndKeyword(boardSearchForm.getKeyword());

        for(Board board : boards){

            if(board.getBoardType() != BoardType.CLINICDIARY) {

                BoardListMainDto boardListMainDto = BoardListMainDto.to(board);
                boardListMainDtos.add(boardListMainDto);

                //게시글 썸네일 이미지 가져오기
                BoardFile boardFile = boardFileRepository.findByBoardIdAndThumbnailYn(board.getId(), "Y");

                //!!: 첨부파일이 없는 게시글에는 기본 이미지 세팅해주기
                if (boardFile != null) {
                    boardListMainDto.setFileUrl(boardFile.getFileUrl());
                } else {
                    boardListMainDto.setFileUrl("/anicareFile/default-thumbnail.png");
                }

                //게시글 카테고리 가져오기 (페이지에 표시해줄 것)
                if (board.getBoardType().name().equals("MEETING")) {
                    MeetingBoard mCategory = meetingBoardRepository.findByBoardId(board.getId());

                    boardListMainDto.setCategory(mCategory.getMeetingCategory().toString());

                } else if (board.getBoardType().name().equals("ERRAND")) {
                    ErrandBoard eCategory = errandBoardRepository.findByBoardId(board.getId());

                    boardListMainDto.setCategory(eCategory.getErrandCategory().toString());
                }

            }

        }

        return boardListMainDtos;
    }


}