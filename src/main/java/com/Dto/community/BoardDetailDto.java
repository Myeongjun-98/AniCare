package com.Dto.community;

import com.constant.community.BoardType;
import com.entity.community.Board;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

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

    public static ModelMapper modelMapper = new ModelMapper();

    //entity -> dto
    public static BoardDetailDto of(Board board, List<CommentViewDto> commentList){
        BoardDetailDto boardDetailDto = modelMapper.map(board, BoardDetailDto.class);
        boardDetailDto.setCommentList(commentList);
        return boardDetailDto;
    }


}
