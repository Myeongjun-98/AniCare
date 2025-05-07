package com.AniCare.demo.Dto.community;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardFileDto {

    private Long fileId; //첨부파일 아이디
    private String fileOriginalName; //첨부파일 원본파일명
    private String fileSavedName; //첨부파일 저장명
    private String fileUrl; //첨부파일 url
    private String thumbnailYn; //썸네일 여부
}
