package com.AniCare.demo.Dto.admin;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
    @AllArgsConstructor
    public class NoticeListDto {
        private Long id;
        private String title;
        private LocalDate date;
        private String category;
        private String summary;
    private String content;
    }

