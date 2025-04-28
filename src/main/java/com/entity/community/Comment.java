package com.entity.community;

import com.entity.entity.MainPage.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Comment { //덧글 정보를 저장하는 Entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long commentId; //덧글 아이기

    @ManyToOne
    @JoinColumn(name="board_id")
    private  Board board; //게시글 아이디

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user; //유저 아이디

    @Column(nullable = false, length = 200)
    private String commentContent; //덧글 내용(최대 200자)

    private LocalDate commentWriteDate; //덧글 작성일
}
