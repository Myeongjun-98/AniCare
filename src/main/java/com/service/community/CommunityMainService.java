package com.service.community;

import com.Dto.community.BoardListMainDto;
import com.entity.community.Board;
import com.repository.MainPage.UserRepository;
import com.repository.community.BoardFileRepository;
import com.repository.community.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityMainService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    private final UserRepository userRepository;

    public List<BoardListMainDto> getBoardList(){
        List<BoardListMainDto> boardListMainDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByBoardHitDesc();

        for(Board board : boards){
            BoardListMainDto boardListMainDto = BoardListMainDto.of(board);
            boardListMainDtos.add(boardListMainDto);

        }

        return boardListMainDtos;
    }



}
