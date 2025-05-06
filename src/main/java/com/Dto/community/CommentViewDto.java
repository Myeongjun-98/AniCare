package com.Dto.community;

import com.entity.community.Comment;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Getter @Setter
public class CommentViewDto {

    private Long commentId; //덧글 아이디
    private Long boardId; //게시글 아이디
    private String userName; //덧글 작성자 이름
    private LocalDate commentWriteDate; //덧글 작성일
    private String commentContent; //덧글 내용

    public static ModelMapper modelMapper = new ModelMapper();

    public static CommentViewDto from(Comment comment){
        return modelMapper.map(comment, CommentViewDto.class);
    }
}
