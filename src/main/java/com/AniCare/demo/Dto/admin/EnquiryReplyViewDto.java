package com.AniCare.demo.Dto.admin;
import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.admin.EnquiryReply;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryReplyViewDto {
    private Long userId;
    private Long id;             // 문의 ID
    private String title;        // 문의 제목
    private String user;         // 작성자 아이디
    private String content;      // 문의 내용
    private String status;       // 처리 상태 (Enquiry 기준으로 저장)
    private String reply;        // 관리자 답변 내용
    private String createDate;   // 작성일
    private Long enquiryReplyId; // 답변 ID

    public static EnquiryReplyViewDto of(EnquiryReply enquiryReply, Enquiry enquiry) {
        EnquiryReplyViewDto dto = new EnquiryReplyViewDto();
        dto.setId(enquiry.getId());
        dto.setUser(String.valueOf(enquiry.getUser())); // 필요시 getUser().getName() 등 변경
        dto.setUserId(enquiry.getUser().getUserId());
        dto.setTitle(enquiry.getEnquiryTitle());
        dto.setContent(enquiry.getEnquiryContent());
        dto.setCreateDate(String.valueOf(enquiry.getEnquiryDate()));
        dto.setEnquiryReplyId(enquiryReply.getId());
        dto.setReply(enquiryReply.getContent());

        // ✅ 핵심: Enquiry의 상태를 사용해야 "처리완료"가 반영됨
        dto.setStatus(String.valueOf(enquiry.getStatus()));

        return dto;
    }
}
