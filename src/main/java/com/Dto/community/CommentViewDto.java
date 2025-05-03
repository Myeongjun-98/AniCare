package com.Dto.community;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CommentViewDto {

    private Long commentId; //덧글 아이디
    private String userName; //덧글 작성자 이름
    private LocalDate commentWriteDate; //덧글 작성일
    private String commentContent; //덧글 내용
}
