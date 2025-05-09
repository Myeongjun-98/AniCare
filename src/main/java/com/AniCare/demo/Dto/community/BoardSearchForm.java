package com.AniCare.demo.Dto.community;

import com.AniCare.demo.constant.community.BoardType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearchForm {

    private String keyword; //검색 키워드

    private BoardType boardType; //게시판 타입(모임 모집글, 심부름 구인글)

    private String boardTitle; //게시판 제목

    private String boardContent; //게시판 내용
}
