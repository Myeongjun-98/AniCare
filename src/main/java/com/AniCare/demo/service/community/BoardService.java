package com.AniCare.demo.service.community;

import com.AniCare.demo.Dto.community.BoardDetailDto;
import com.AniCare.demo.Dto.community.BoardForm;
import com.AniCare.demo.Dto.community.BoardListMainDto;
import com.AniCare.demo.Dto.community.CommentViewDto;
import com.AniCare.demo.constant.community.ErrandCategory;
import com.AniCare.demo.constant.community.MeetingCategory;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.entity.community.Comment;
import com.AniCare.demo.entity.community.ErrandBoard;
import com.AniCare.demo.entity.community.MeetingBoard;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.community.*;
import com.repository.community.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final MeetingBoardRepository meetingBoardRepository;
    private final ErrandBoardRepository errandBoardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    // ================ 커뮤니티 - 게시글 목록 불러오기 (현재 첨부파일 미구현) ================
    public List<BoardListMainDto> getBoardList() {
        List<BoardListMainDto> boardListMainDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByIdDesc();


        for (Board board : boards) {

            //게시글 작성자 관련 정보 갖고 오기
            User userInfo = userRepository.getById(board.getUser().getId());

            //게시글 정보 가져오기
            BoardListMainDto boardListMainDto = BoardListMainDto.to(board);

            //게시글 작성자 정보 가져오기
            boardListMainDto.setUserName(userInfo.getUserName());
            boardListMainDto.setUserAddress(userInfo.getUserAddress());

            //게시글 카테고리 가져오기
            if (board.getBoardType().name() == "MEETING") {
                MeetingBoard mCategory = meetingBoardRepository.findByBoardId(board.getId());

                boardListMainDto.setCategory(mCategory.getMeetingCategory().toString());

            } else if (board.getBoardType().name() == "ERRAND") {
                ErrandBoard eCategory = errandBoardRepository.findByBoardId(board.getId());

                boardListMainDto.setCategory(eCategory.getErrandCategory().toString());
            }

            //가져온 데이터들 전부 넣어주기
            boardListMainDtos.add(boardListMainDto);

        }
        return boardListMainDtos;
    }

    // ================ 커뮤니티 - 게시글 상세정보 보기 ================
    public BoardDetailDto getBoardDetail(Long boardId) {
        //게시글 정보 불러오기
        Board board = boardRepository.findById(boardId).get();
        User userBoardInfo = userRepository.getById(board.getUser().getId());


        //게시글 관련 덧글목록 불러오기
        List<Comment> commentList = commentRepository.findByBoardId(boardId);

        List<CommentViewDto> commentViewDtos = new ArrayList<>();
        for (Comment comment : commentList) {
            User userCommentInfo = userRepository.getById(comment.getUser().getId());
            CommentViewDto commentViewDto = CommentViewDto.from(comment);
            commentViewDto.setUserName(userCommentInfo.getUserName());
            commentViewDtos.add(commentViewDto);
        }

        BoardDetailDto boardDetailDto = BoardDetailDto.of(board, commentViewDtos);

        //게시글 작성자 정보 불러오기
        boardDetailDto.setUserName(userBoardInfo.getUserName());
        boardDetailDto.setUserAddress(userBoardInfo.getUserAddress());

        //게시글 덧글, 좋아요 수 불러오기
        int likeCount = boardLikeRepository.countByBoardId(boardId);
        int comCount = commentRepository.countByBoardId(boardId);

        boardDetailDto.setLikeCount(likeCount);
        boardDetailDto.setCommentCount(comCount);


        //게시글 카테고리 불러오기(모임 모집글)
        if (boardDetailDto.getBoardType().name() == "MEETING")
            boardDetailDto.setCategory(meetingBoardRepository
                    .findByBoardId(boardDetailDto.getBoardId())
                    .getMeetingCategory()
                    .toString());

        //게시글 카테고리 불러오기(심부름 구인글)
        if (boardDetailDto.getBoardType().name() == "ERRAND")
            boardDetailDto.setCategory(errandBoardRepository
                    .findByBoardId(boardDetailDto.getBoardId())
                    .getErrandCategory()
                    .toString());

        return boardDetailDto;
    }


    // ================ 커뮤니티 - 게시글 작성 (현재 첨부파일 미구현) ================
    public void boardSave(BoardForm boardForm, String category) {

        //board타입 제목, 내용 저장
        Board board = boardForm.to();
        ErrandBoard errandBoard = new ErrandBoard();
        MeetingBoard meetingBoard = new MeetingBoard();

        boardForm.setUserId(2L);
        User user = userRepository.findById(boardForm.getUserId()).get();
        board.setUser(user);

        boardRepository.save(board);

        //errand, meeting
        if (boardForm.getBoardType().name() == "MEETING") {
            MeetingCategory meetingCategory = MeetingCategory.valueOf(boardForm.getCategory());
            meetingBoard.setBoard(board);
            meetingBoard.setMeetingCategory(meetingCategory);
            meetingBoardRepository.save(meetingBoard);
        } else if (boardForm.getBoardType().name() == "ERRAND") {
            ErrandCategory errandCategory = ErrandCategory.valueOf(boardForm.getCategory());
            errandBoard.setBoard(board);
            errandBoard.setErrandCategory(errandCategory);
            errandBoardRepository.save(errandBoard);
        }


    }


}
