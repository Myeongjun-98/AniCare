package com.AniCare.demo.Dto.admin;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EnquiryReplyDto {
    private Long enquiryReplyId;      // 답변 ID
    private Long enquiryId;           // 문의 ID
    private Long userId;              // 작성자 ID
    private String userName;          // 작성자 이름
    private String enquiryTitle;      // 문의 제목
    private String enquiryContent;    // 문의 내용
    private String replyContent;      // 답변 내용
    private LocalDate replyDate;      // 답변 작성일
    private String status;            // 처리 상태 (예: 처리완료/처리대기)

    /**
     * EnquiryReply + Enquiry 엔티티에서 DTO로 변환 시 사용
     */
    public static EnquiryReplyDto of(Long enquiryReplyId,
                                     Long enquiryId,
                                     Long userId,
                                     String userName,
                                     String enquiryTitle,
                                     String enquiryContent,
                                     String replyContent,
                                     LocalDate replyDate,
                                     String status) {
        EnquiryReplyDto dto = new EnquiryReplyDto();
        dto.setEnquiryReplyId(enquiryReplyId);
        dto.setEnquiryId(enquiryId);
        dto.setUserId(userId);
        dto.setUserName(userName);
        dto.setEnquiryTitle(enquiryTitle);
        dto.setEnquiryContent(enquiryContent);
        dto.setReplyContent(replyContent);
        dto.setReplyDate(replyDate);
        dto.setStatus(status);
        return dto;
    }
}
