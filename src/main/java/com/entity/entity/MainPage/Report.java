package com.entity.entity.MainPage;

import com.entity.community.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Report {

    // 신고 테이블 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    // 게시글 아이디
    @JoinColumn(name = "board_id")
    @ManyToOne
    private Board board;

    // 신고자 아이디
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private LocalDate reportDate;


}
