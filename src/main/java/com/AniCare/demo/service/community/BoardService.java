package com.AniCare.demo.service.community;

import com.AniCare.demo.Dto.community.*;
import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.community.*;
import com.AniCare.demo.repository.community.*;
import com.AniCare.demo.constant.community.ErrandCategory;
import com.AniCare.demo.constant.community.MeetingCategory;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Comparator;
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

    private final BoardFileService boardFileService;

    // ================ 메인페이지에서 커뮤니티 인기글 불러오기 위한 메서드 =================
    public List<Board> getAllboardList(){
        List<Board> boards = boardRepository.findAll();

        return boards;
    }


    // ================ 커뮤니티 - 게시판 페이지 - 게시글 목록 불러오기 ================
    public List<BoardListMainDto> getBoardList(String type, String order, String category){

        List<BoardListMainDto> boardListMainDtos = new ArrayList<>();

        //게시판 타입 구하기
        BoardType boardType = BoardType.valueOf(type);

        List<Board> boards = boardRepository.findAllByBoardTypeOrderByIdDesc(boardType);

        for(Board board : boards) {
            if(board.getBoardType() != BoardType.CLINICDIARY) {

                //게시글 작성자 관련 정보 갖고 오기
                User userInfo = userRepository.findById(board.getUser().getId()).orElse(null);

                //게시글 정보 가져오기
                BoardListMainDto boardListMainDto = BoardListMainDto.to(board);


                //게시글 작성자 정보 가져오기
                boardListMainDto.setUserName(userInfo.getUserName());
                boardListMainDto.setUserAddress(userInfo.getUserAddress());
                boardListMainDto.setUserFile(userInfo.getUserImage());

                //게시글 첨부파일 이미지 가져오기
                BoardFile boardFile = boardFileRepository.findByBoardIdAndThumbnailYn(board.getId(), "Y");

                //!!: 첨부파일이 없는 게시글에는 기본 이미지 세팅해주기
                if (boardFile != null) {
                    boardListMainDto.setFileUrl(boardFile.getFileUrl());
                } else {
                    boardListMainDto.setFileUrl("/anicareFile/default-thumbnail.png");
                }


                //게시글 카테고리 가져오기
                if (board.getBoardType().name().equals("MEETING")) {
                    MeetingBoard mCategory = meetingBoardRepository.findByBoardId(board.getId());

                    boardListMainDto.setCategory(mCategory.getMeetingCategory().toString());

                } else if (board.getBoardType().name().equals("ERRAND")) {
                    ErrandBoard eCategory = errandBoardRepository.findByBoardId(board.getId());

                    boardListMainDto.setCategory(eCategory.getErrandCategory().toString());
                }


                //가져온 데이터들 전부 dto에 넣어주기
                boardListMainDtos.add(boardListMainDto);
            }
        }

        //인기순 정렬 기능
        if(order.equals("L")) {
            boardListMainDtos.sort(Comparator.comparing(BoardListMainDto::getLikeCount, Comparator.reverseOrder()));
        }

        return boardListMainDtos;
    }


    // ================ 커뮤니티 - 게시글 상세정보 보기 ================
    public BoardDetailDto getBoardDetail(Long boardId){

        //게시글 정보 불러오기
        Board board = boardRepository.findById(boardId).orElse(null);
        User userBoardInfo = userRepository.findById(board.getUser().getId()).orElse(null);


        //게시글 관련 덧글목록 불러오기
        List<Comment> commentList = commentRepository.findByBoardIdOrderByIdDesc(boardId);

        List<CommentViewDto> commentViewDtos = new ArrayList<>();
        for(Comment comment : commentList) {
            User userCommentInfo = userRepository.findById(comment.getUser().getId()).orElse(null);
            CommentViewDto commentViewDto = CommentViewDto.from(comment);
            commentViewDto.setUserName(userCommentInfo.getUserName());
            commentViewDtos.add(commentViewDto);
        }

        //게시글 내 첨부파일 전부 불러오기
        List<BoardFile> boardFiles = boardFileRepository.findAllByBoardId(boardId);

        List<BoardFileDto> boardFileDtos = new ArrayList<>();
        for(BoardFile boardFile : boardFiles) {
            boardFileDtos.add(BoardFileDto.from(boardFile));
        }

        BoardDetailDto boardDetailDto = BoardDetailDto.of(board, commentViewDtos, boardFileDtos);

        //게시글 작성자 정보 불러오기
        boardDetailDto.setUserName(userBoardInfo.getUserName());
        boardDetailDto.setUserAddress(userBoardInfo.getUserAddress());
        boardDetailDto.setUserImage(userBoardInfo.getUserImage());

        //게시글 덧글수 불러오기
        int comCount = commentRepository.countByBoardId(boardId);
        boardDetailDto.setCommentCount(comCount);


        //게시글 카테고리 불러오기(모임 모집글)
        if(boardDetailDto.getBoardType().name().equals("MEETING"))
        { boardDetailDto.setCategory(meetingBoardRepository
                .findByBoardId(boardId)
                .getMeetingCategory()
                .toString()); }

        //게시글 카테고리 불러오기(심부름 구인글)
        if(boardDetailDto.getBoardType().name().equals("ERRAND"))
        { boardDetailDto.setCategory(errandBoardRepository
                .findByBoardId(boardId)
                .getErrandCategory()
                .toString()); }


        return boardDetailDto;
    }


    // ================ 커뮤니티 - 게시글 업로드 ================
    public void boardSave(BoardForm boardForm,
                          List<MultipartFile> multipartFileList, String email)
            throws Exception{

        //board타입 제목, 내용 저장
        Board board = boardForm.to();
        ErrandBoard errandBoard = new ErrandBoard();
        MeetingBoard meetingBoard = new MeetingBoard();

        //회원정보 User에 담아주기
        User user = userRepository.findByUserEmail(email).orElse(null);
        board.setUser(user);

        boardRepository.save(board);

        //errand, meeting 테이블에 저장
        if(boardForm.getBoardType().name().equals("MEETING"))
        {
            MeetingCategory meetingCategory = MeetingCategory.valueOf(boardForm.getCategory());
            meetingBoard.setBoard(board);
            meetingBoard.setMeetingCategory(meetingCategory);
            meetingBoardRepository.save(meetingBoard);
        }
        else if(boardForm.getBoardType().name().equals("ERRAND")) {
            ErrandCategory errandCategory = ErrandCategory.valueOf(boardForm.getCategory());
            errandBoard.setBoard(board);
            errandBoard.setErrandCategory(errandCategory);
            errandBoardRepository.save(errandBoard);
        }

        //이미지 업로드 -> board_file 테이블 저장
        for(int i=0; i< multipartFileList.size(); i++){

            //첨부파일 배열에 빈칸이 있다면 데이터 저장 X
            MultipartFile file = multipartFileList.get(i);
            if(file.isEmpty()){
                continue;
            }

            BoardFile boardFile = new BoardFile();
            boardFile.setBoard(board); //board_file의 board(board_id)값 지정해주기

            //썸네일 이미지 정해주기
            if(i==0)
                boardFile.setThumbnailYn("Y");
            else
                boardFile.setThumbnailYn("N");


            //BoardFileService 메서드 호출
            //테이블 저장 작업이 이루어지는 코드
            boardFileService.saveFile(boardFile, multipartFileList.get(i));
        }


    }




}