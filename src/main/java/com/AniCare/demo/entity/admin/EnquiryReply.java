package com.AniCare.demo.entity.admin;

import com.AniCare.demo.DTO.admin.EnquiryReplyViewDto;
import com.AniCare.demo.entity.MainPage.Enquiry;
import jakarta.persistence.*;
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

    public EnquiryReplyViewDto to() {
        EnquiryReplyViewDto dto = new EnquiryReplyViewDto();
        dto.setId(this.id);
        dto.setTitle(this.enquiry.getEnquiryTitle());
        dto.setUserId(this.enquiry.getUser().getLoginId());
        dto.setContent(this.enquiry.getEnquiryContent());
        dto.setStatus(this.enquiry.getStatus());
        dto.setReply(this.content);
        dto.setCreateDate(this.createDate != null ? this.createDate.toString() : null);
        return dto;
    }
}
