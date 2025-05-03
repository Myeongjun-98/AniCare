package com.Dto.community;

import com.entity.community.Comment;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Getter @Setter
public class CommentForm {
    private Long commentId; //덧글 아이디
    private Long boardId; //게시글 아이디
    private String commentContent; //덧글 내용
    private LocalDate commentWriteDate; //덧글 작성일

    public static ModelMapper modelMapper = new ModelMapper();

    public Comment to(){
    return modelMapper.map(this, Comment.class);
    }
}
