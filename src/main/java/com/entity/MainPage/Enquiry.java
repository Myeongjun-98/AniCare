package com.entity.MainPage;

import com.constant.MainPage.EnquiryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    private Long enquiryId;
    
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
    private EnquiryType enquiryType; // 문의사항 유형


}
