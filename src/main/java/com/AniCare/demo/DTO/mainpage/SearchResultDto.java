package com.AniCare.demo.Dto.mainpage;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SearchResultDto {

    private String title;
    private String url;
    private String category;
    private int views;
    private LocalDate createAt; // 생성일(최신순 정렬용)


}
