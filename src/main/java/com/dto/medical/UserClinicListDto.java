package com.dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class UserClinicListDto {

    private Long clinicId;  // 진료기록 식별용 id
    private LocalDateTime clinicDate;   // 진료 시간
    private String petName; // 동물 이름
    private String diagnosedDiseaseAndAllergy;  // 진단된 병명과 알러지명을 toString으로 간략화
    private String vetName; // 수의사 이름

}
