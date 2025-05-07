package com.AniCare.demo.DTO.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryReplyViewDto {

    private Long id;           // 문의 ID
    private String title;      // 문의 제목
    private String userId;     // 작성자 로그인 ID
    private String content;    // 문의 내용
    private String status;     // 처리 상태 (기본: 처리완료로 설정하거나, 엔티티에 필드 추가)
    private String reply;      // 관리자 답변 내용
    private String createDate; // 작성일 (yyyy-MM-dd 등 형식)
}
