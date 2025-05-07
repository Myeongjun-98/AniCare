package com.AniCare.demo.DTO.admin;

import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.admin.EnquiryReply;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryReplyViewDto {

    private Long id;           // 문의 ID
    private String title;      // 문의 제목
    private String user;     // 작성자아아디
    private String content;    // 문의 내용
    private String status;     // 처리 상태 (기본: 처리완료로 설정하거나, 엔티티에 필드 추가)
    private String reply;      // 관리자 답변 내용
    private String createDate; // 작성일 (yyyy-MM-dd 등 형식)
    private Long EnquiryReplyId;// 문의게시글 아이디
    public static EnquiryReplyViewDto of(EnquiryReply enquiryReply, Enquiry enquiry){
        EnquiryReplyViewDto enquiryReplyViewDto=new EnquiryReplyViewDto();
        enquiryReplyViewDto.setId(enquiry.getId());
        enquiryReplyViewDto.setUser(String.valueOf(enquiry.getUser()));
        enquiryReplyViewDto.setTitle(enquiry.getEnquiryTitle());
        enquiryReplyViewDto.setContent(enquiry.getEnquiryContent());
        enquiryReplyViewDto.setCreateDate(String.valueOf(enquiry.getEnquiryDate()));
        enquiryReplyViewDto.setEnquiryReplyId(enquiryReply.getId());
        enquiryReplyViewDto.setReply(enquiryReply.getContent());
        enquiryReplyViewDto.setStatus(enquiryReply.getStatus());

        return enquiryReplyViewDto;
    }
}

