package com.AniCare.demo.entity.admin;

import com.AniCare.demo.entity.MainPage.Enquiry;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EnquiryReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "enquiry_id")
    private Enquiry enquiry;

    private String content;

    @Column(nullable = false)
    private LocalDate createDate;


}
