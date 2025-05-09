package com.AniCare.demo.DTO.community;

import com.AniCare.demo.entity.community.BoardFile;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class BoardFileDto {

    public static ModelMapper modelMapper = new ModelMapper();
    private Long fileId; //첨부파일 아이디
    private String fileOriginalName; //첨부파일 원본파일명
    private String fileSavedName; //첨부파일 저장명
    private String fileUrl; //첨부파일 url
    private String thumbnailYn; //썸네일 여부

    //entity -> dto
    public static BoardFileDto from(BoardFile boardFile) {
        return modelMapper.map(boardFile, BoardFileDto.class);
    }
}
