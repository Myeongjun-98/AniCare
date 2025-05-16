package com.AniCare.demo.Dto.admin;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class EnquiryReplyDto {
    private Long id;
    private Long enquiryId;
    private String content;
    private LocalDate createDate;
    private String status;

}
