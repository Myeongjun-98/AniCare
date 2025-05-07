package com.AniCare.demo.entity.admin;

import com.AniCare.demo.constant.admin.NoticeCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String noticeTitle;

    @Column(nullable = false)
    private LocalDate noticeDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String noticeBody;

    @Enumerated(EnumType.STRING)
    private NoticeCategory noticeCategory;

}
