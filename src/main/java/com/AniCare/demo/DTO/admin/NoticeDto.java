package com.AniCare.demo.DTO.admin;

import com.AniCare.demo.constant.admin.NoticeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
    private String title;
    private String body;
    private NoticeCategory category;
    
    /// / 그냥쓰는말
}
