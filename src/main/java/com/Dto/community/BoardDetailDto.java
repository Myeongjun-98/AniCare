package com.Dto.community;

import com.constant.community.BoardType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class BoardDetailDto {
    private Long boardId; //게시글 아이디

//    private List<BoardFileDto> boardFileDtos; //게시글 첨부파일

    private BoardType boardType; //게시글 타입
    private String boardTitle; //게시글 제목
    private LocalDate boardWriteDate; //게시글 작성일
    private int boardHit; //게시글 조회수
    private String boardContent; //게시글 내용
    private String userName; //게시글 작성자 이름
    private String userAddress; //게시글 작성자 주소

    private List<CommentViewDto> commentList; //게시글 덧글 목록
}
