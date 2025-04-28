package com.entity.community;

import com.constant.community.BoardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Board { //게시글 정보를 저장하는 Entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long boardId; //게시글 아이디

    @Column(nullable=false)
    private String boardTitle; //게시글 제목

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user; //작성자 아이디

    @Column(nullable=false, length = 500)
    private String boardContent; //게시글 내용

    private int boardHit; //게시글 조회수

    private LocalDate boardWriteDate; //게시글 작성일

    @Enumerated(EnumType.STRING)
    private BoardType boardType; //게시판 타입
}
