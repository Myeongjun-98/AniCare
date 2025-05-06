package com.Dto.community;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSumDto {
    private Long boardId; //게시글 id
    private int likeCount; //좋아요 수

//    public BoardSumDto(Long boardId, Long likeCount){
//        this.boardId = boardId;
//        this.likeCount = likeCount.intValue();
//    }
    
}
