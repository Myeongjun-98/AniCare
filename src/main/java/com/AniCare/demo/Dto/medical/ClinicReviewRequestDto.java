package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ClinicReviewRequestDto {

    private Long clinicId;  // 식별용 진료 아이디
    private float rating;   // 별점수치

}
