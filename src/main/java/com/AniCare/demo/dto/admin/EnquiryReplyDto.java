package com.AniCare.demo.dto.admin;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class EnquiryReplyDto {
    private Long id;
    private Long enquiryId;
    private Long userId;
    private String content;
    private LocalDate createDate;
    private String status;

}
