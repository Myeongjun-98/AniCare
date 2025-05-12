package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VetAverageReviewDto {
    private Long vetInfoId; // 수의사 정보 아이디
    private double averageRating;    // 별점 평균값

}
