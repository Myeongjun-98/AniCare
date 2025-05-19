package com.AniCare.demo.Dto.community;

import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.entity.community.BoardLike;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardLikeForm {

    private Long id; //좋아요 아이디


    private Long userId; //좋아요 누른 사용자 아이디


    private Long boardId; //좋아요된 게시글 아이디

}
