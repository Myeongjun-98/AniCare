package com.dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@AllArgsConstructor@Setter@Getter
public class ClinicDeliveryRequestDto {

    private Long clinicId;      // 식별용 진료 아이디
    private float rating;       // 별점수치

}
