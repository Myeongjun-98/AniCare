package entity.MainPage;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Enquiry {
    
    // 공지사항 테이블 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enquiryId;
    
    // 유저테이블 아이디
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    
    private String enquiryTitle; // 문의사항 제목
    private String enquiryContent; // 문의사항 내용
    private LocalDate EnquiryDate; // 문의사항 작성일
    private String enquiryFile; // 문의사항 첨부파일
    
    
}
