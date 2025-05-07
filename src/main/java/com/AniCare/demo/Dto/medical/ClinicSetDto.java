package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class ClinicSetDto {

    private Long scheduleId;    // 스케쥴 아이디
    private Long diagnosisInfoId;   // 진단정보 아이디
    private String opinion; // 작성된 소견

}
