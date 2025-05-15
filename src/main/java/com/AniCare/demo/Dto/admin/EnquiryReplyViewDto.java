package com.AniCare.demo.Dto.admin;

import com.AniCare.demo.constant.MainPage.EnquiryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EnquiryReplyViewDto {
    private Long id;
    private Long enquiryId;
    private String enquiryTitle;
    private String enquiryContent;
    private String user;
    private LocalDate createDate;
    private String reply;

    // ✅ 이 필드와 관련 getter/setter가 없으면 컴파일 오류 발생
    private EnquiryStatus status;

    public static EnquiryReplyViewDto of(com.AniCare.demo.entity.admin.EnquiryReply reply,
                                         com.AniCare.demo.entity.MainPage.Enquiry enquiry) {
        EnquiryReplyViewDto dto = new EnquiryReplyViewDto();
        dto.setEnquiryId(enquiry.getId());
        dto.setEnquiryTitle(enquiry.getEnquiryTitle());
        dto.setEnquiryContent(enquiry.getEnquiryContent());
        dto.setUser(enquiry.getUser().getUserName());
        dto.setCreateDate(enquiry.getEnquiryDate());
        dto.setStatus(reply.getEnquiryStatus() != null ? reply.getEnquiryStatus() : EnquiryStatus.미완료); // setter 호출
        dto.setReply(reply.getContent());
        return dto;
    }
}
