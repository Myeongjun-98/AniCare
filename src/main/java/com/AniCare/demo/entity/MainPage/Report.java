package com.AniCare.demo.entity.MainPage;

import com.AniCare.demo.constant.MainPage.ReportType;
import com.AniCare.demo.entity.community.Board;
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
    @Column(name = "report_id")
    private Long id;

    // 게시글 아이디
    @JoinColumn(name = "board_id")
    @ManyToOne
    private Board board;

    // 신고자 아이디
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user; // user 테이블 id

    private LocalDate reportDate; // 신고 일시

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType reportType; // 신고유형


}
