package com.AniCare.demo.DTO.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VetReviewSetDto {

    private Long clinicId;  // 평가할 진료 id
    private double rating;   // 부여할 별점

}
