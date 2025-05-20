package com.AniCare.demo.Dto.community;
import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.community.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BoardForm {
    private Long boardId; //게시글 아이디

    private Long userId; //작성자 아이디

    @NotNull(message = "게시판 타입을 선택해주세요.")
    private BoardType boardType; //게시글 타입

    @NotBlank(message = "게시판 카테고리를 선택해주세요.")
    private String category; //게시글 카테고리

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String boardTitle; //게시글 제목

    @NotEmpty(message = "게시글 내용을 입력해주세요.")
    private String boardContent; //게시글 내용

    public Board to() {
        Board board = new Board();
        board.setBoardType(this.getBoardType());
        board.setBoardTitle(this.getBoardTitle());
        board.setBoardContent(this.getBoardContent());
        board.setBoardWriteDate(LocalDate.now());

        return board;
    }

}


