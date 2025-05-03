package com.service.community;

import com.Dto.community.BoardDetailDto;
import com.Dto.community.BoardListMainDto;
import com.Dto.community.CommentViewDto;
import com.entity.MainPage.User;
import com.entity.community.Board;
import com.entity.community.Comment;
import com.repository.MainPage.UserRepository;
import com.repository.community.BoardFileRepository;
import com.repository.community.BoardLikeRepository;
import com.repository.community.BoardRepository;
import com.repository.community.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
//    private final BoardLikeRepository boardLikeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    //게시글 목록 불러오기 (미완성)
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

    //게시글 상세정보 보기
    public BoardDetailDto getBoardDetail(Long boardId){
        //게시글 정보 불러오기
        Board board = boardRepository.findById(boardId).get();
        User userBoardInfo = userRepository.getById(board.getUser().getId());


        //게시글 관련 덧글목록 불러오기
        List<Comment> commentList = commentRepository.findByBoardId(boardId);

        List<CommentViewDto> commentViewDtos = new ArrayList<>();
        for(Comment comment : commentList) {
            User userCommentInfo = userRepository.getById(comment.getUser().getId());
            CommentViewDto commentViewDto = CommentViewDto.from(comment);
            commentViewDto.setUserName(userCommentInfo.getUserName());
            commentViewDtos.add(commentViewDto);
        }

        BoardDetailDto boardDetailDto = BoardDetailDto.of(board, commentViewDtos);
        //게시글 작성자 정보 불러오기
        boardDetailDto.setUserName(userBoardInfo.getUserName());
        boardDetailDto.setUserAddress(userBoardInfo.getUserAddress());

        return boardDetailDto;
    }
}
