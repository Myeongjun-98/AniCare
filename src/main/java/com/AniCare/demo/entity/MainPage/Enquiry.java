package com.AniCare.demo.entity.MainPage;

import com.AniCare.demo.constant.MainPage.EnquiryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enquiry_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String enquiryTitle;

    @Column(nullable = false)
    private String enquiryContent;

    private LocalDate enquiryDate;

    private String enquiryFile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnquiryType enquiryType;

    @Column(nullable = false)
    private String status = "대기";
}
