package com.AniCare.demo.Dto.community;

import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.community.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BoardListSubDto {
    private Long id; //게시글 아이디
    //    private String fileUrl; //게시글 썸네일 이미지
    private String boardTitle; //게시글 제목
    private BoardType boardType; //게시글 타입
    private LocalDate boardWriteDate; //게시글 작성일
    private int likeCount; //좋아요 수

    private String Category; //게시글 카테고리


    //entity -> dto
    public static BoardListSubDto to(Board board, int likeCount) {
        BoardListSubDto boardListSubDto = new BoardListSubDto();

        boardListSubDto.setId(board.getId());
        boardListSubDto.setBoardTitle(board.getBoardTitle());
        boardListSubDto.setBoardType(board.getBoardType());
        boardListSubDto.setBoardWriteDate(board.getBoardWriteDate());
        boardListSubDto.setLikeCount(likeCount);

        return boardListSubDto;
    }

}

