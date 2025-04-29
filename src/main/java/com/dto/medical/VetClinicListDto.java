package com.dto.medical;

import com.constant.medical.PetSex;
import com.constant.medical.PetSpecies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class VetClinicListDto {

    private Long clinicId;      // 진료기록 식별용 id
    private LocalDateTime clinicDate;   // 진료시간
    private String userName;    // 사용자 이름
    private String petName;     // 동물 이름
    private PetSpecies petSpecies;  // 동물 종
    private PetSex petSex;      // 동물 성별종류
    private String diagnosedDiseaseAndAllergy;  // 진단된 병명과 알러지명을 toString으로 간략화

}
