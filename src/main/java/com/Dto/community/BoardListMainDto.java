package com.Dto.community;

import com.constant.community.BoardType;
import com.entity.MainPage.User;
import com.entity.community.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BoardListMainDto {

    private Long id; //게시글 아이디

    private String fileUrl; //게시글 썸네일 이미지

    private String boardTitle; //게시글 제목

    private BoardType boardType; //게시글 타입

    private String Category; //게시글 카테고리

    private String boardContent; //게시글 내용

    private LocalDate boardWriteDate; //게시글 작성일

    private int boardHit; //게시글 조회수

//    private String userFile; //작성자 프로필이미지

    private String userName; //게시글 작성자

    private String userAddress; //작성자 주소


//entity -> dto
public static BoardListMainDto to(Board board){
    BoardListMainDto boardListMainDto = new BoardListMainDto();

    boardListMainDto.setId(board.getId());
    boardListMainDto.setBoardTitle(board.getBoardTitle());
    boardListMainDto.setBoardType(board.getBoardType());
    boardListMainDto.setBoardContent(board.getBoardContent());
    boardListMainDto.setBoardWriteDate(board.getBoardWriteDate());
    boardListMainDto.setBoardHit(board.getBoardHit());


    return boardListMainDto;
}


}

