package com.service.community;

import com.Dto.community.BoardListMainDto;
import com.entity.MainPage.User;
import com.entity.community.Board;
import com.repository.MainPage.UserRepository;
import com.repository.community.BoardFileRepository;
import com.repository.community.BoardLikeRepository;
import com.repository.community.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final UserRepository userRepository;


    public List<BoardListMainDto> getBoardList(){
        List<BoardListMainDto> boardListMainDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByBoardHitDesc();


        for(Board board : boards){

            //게시글 작성자 관련 정보 갖고 오기
            User userInfo = userRepository.getById(board.getUser().getId());

            //게시글 정보 가져오기
            BoardListMainDto boardListMainDto = BoardListMainDto.to(board);

            //게시글 작성자 정보 가져오기
            boardListMainDto.setUserName(userInfo.getUserName());
            boardListMainDto.setUserAddress(userInfo.getUserAddress());
            boardListMainDtos.add(boardListMainDto);

        }
        return boardListMainDtos;
    }
}
