package com.entity.admin;

import com.constant.admin.NoticeCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class Notice {
    @Id
    @Column
    @GeneratedValue(strategy=IDENTITY)
    private Long noticeId;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String noticeTitle;
    @Column(nullable = false)
    private LocalDate noticeDate;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String noticeBody;
    @Enumerated(EnumType.STRING)
    private NoticeCategory noticeCategory;

}
