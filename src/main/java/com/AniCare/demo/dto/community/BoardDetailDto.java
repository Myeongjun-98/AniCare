package com.AniCare.demo.dto.community;
import com.AniCare.demo.dto.community.BoardDetailDto;
import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.community.Board;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class BoardDetailDto {
    private Long boardId; //게시글 아이디

    private List<BoardFileDto> boardFileDtos; //게시글 첨부파일

    private BoardType boardType; //게시글 타입
    private String Category; //게시글 카테고리
    private String boardTitle; //게시글 제목
    private LocalDate boardWriteDate; //게시글 작성일
    private int boardHit; //게시글 조회수
    private String boardContent; //게시글 내용
    private String userName; //게시글 작성자 이름
    private String userAddress; //게시글 작성자 주소

    private List<CommentViewDto> commentList; //게시글 덧글 목록

    private Integer likeCount; //좋아요 수
    private Integer commentCount; //덧글 수

    public static ModelMapper modelMapper = new ModelMapper();

    //entity -> dto
    public static BoardDetailDto of(Board board, List<CommentViewDto> commentList,
                                    List<BoardFileDto> boardFileDtos){
        BoardDetailDto boardDetailDto = modelMapper.map(board, BoardDetailDto.class);
        boardDetailDto.setCommentList(commentList);
        boardDetailDto.setBoardFileDtos(boardFileDtos);
        return boardDetailDto;
    }


}
