package com.AniCare.demo.Dto.community;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommentForm {
    private Long commentId; //덧글 아이디

    private Long boardId; //게시글 아이디

    private Long userId; //덧글 작성자 아이디

    @NotBlank(message = "덧글 내용을 입력해주세요.")
    private String commentContent; //덧글 내용

    private LocalDate commentWriteDate; //덧글 작성일


}
