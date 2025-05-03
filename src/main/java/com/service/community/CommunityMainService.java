package com.service.community;

import com.Dto.community.BoardListMainDto;
import com.Dto.community.BoardListSubDto;
import com.Dto.community.BoardSumDto;
import com.entity.community.Board;
import com.repository.MainPage.UserRepository;
import com.repository.community.BoardFileRepository;
import com.repository.community.BoardLikeRepository;
import com.repository.community.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class
CommunityMainService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final BoardLikeRepository boardLikeRepository;

    private final UserRepository userRepository;

    //커뮤니티 메인 페이지의 게시글 리스트 불러오기
    public List<BoardListMainDto> getBoardMainList(){
        List<BoardListMainDto> boardListMainDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByBoardHitDesc();

        for(Board board : boards){
            BoardListMainDto boardListMainDto = BoardListMainDto.to(board);
            boardListMainDtos.add(boardListMainDto);

        }

        return boardListMainDtos;
    }

    //커뮤니티 검색결과 페이지 리스트 가져오기
    public List<BoardListSubDto> getBoardSearchList(){
        List<BoardListSubDto> boardListSubDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByIdDesc();

        for(Board board : boards){
            int likeCount = boardLikeRepository.countByBoardId(board.getId());
            BoardListSubDto boardListSubDto = BoardListSubDto.to(board, likeCount);
            boardListSubDtos.add(boardListSubDto);
        }

        return boardListSubDtos;
    }


}
