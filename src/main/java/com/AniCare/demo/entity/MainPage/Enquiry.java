package com.AniCare.demo.entity.MainPage;

import com.AniCare.demo.constant.MainPage.EnquiryStatus;
import com.AniCare.demo.constant.MainPage.EnquiryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Enquiry {

    // 공지사항 테이블 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enquiry_id")
    private Long id;

    // 유저테이블 아이디
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String enquiryTitle; // 문의사항 제목

    @Column(nullable = false)
    private String enquiryContent; // 문의사항 내용

    private LocalDate EnquiryDate; // 문의사항 작성일

    private String enquiryFile; // 문의사항 첨부파일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnquiryType enquiryType; // 문의사항 유형

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnquiryStatus enquiryStatus;


}
