package com.AniCare.demo.entity.admin;

import com.AniCare.demo.Dto.admin.EnquiryReplyDto;
import com.AniCare.demo.constant.MainPage.EnquiryStatus;
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

    private EnquiryStatus enquiryStatus;



    public EnquiryReplyDto to() {
        EnquiryReplyDto dto = new EnquiryReplyDto();
        if (enquiry != null) {
            dto.setEnquiryId(enquiry.getId()); // enquiry가 null일 수도 있으니 체크
        }
        dto.setReplyContent(content);
        dto.setReplyDate(createDate);
        dto.setEnquiryStatus(enquiryStatus);
        return dto;
    }
}
