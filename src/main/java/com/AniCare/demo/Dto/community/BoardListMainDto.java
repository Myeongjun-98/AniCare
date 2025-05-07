package com.AniCare.demo.Dto.community;

import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.community.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BoardListMainDto {

    private Long id; //게시글 아이디

//    private String fileUrl; //게시글 썸네일 이미지

    private String boardTitle; //게시글 제목

    private BoardType boardType; //게시글 타입

    private String boardContent; //게시글 내용

    private LocalDate boardWriteDate; //게시글 작성일

    private int boardHit; //게시글 조회수

//    private String userName; //게시글 작성자

//    private String userAddress; //작성자 주소

    //
public static BoardListMainDto of(Board board){
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

