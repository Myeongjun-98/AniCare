package com.Dto.medical;

import com.constant.medical.PetSex;
import com.constant.medical.PetSpecies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClinicDto {

    private LocalDateTime clinicDate;   // 진료 날짜
    private String vetName;             // 수의사 이름
    private String petImage;            // 반려동물 프로필이미지
    private String userName;            // 사용자(보호자) 이름
    private String petName;             // 반려동물 이름
    private PetSpecies petSpecies;      // 반려동물 동물종
    private PetSex petSex;              // 반려동물 성별종류
    private List<String> diseaseName;   // 진단 병명 리스트
    private List<String> allergyName;   // 진단 알러지 리스트
    private String opinion;             // 작성된 수의사 소견
    private List<String> medicine;      // 처방된 약 리스트
    private List<Integer> amount;       // 처방된 약 리스트의 각 수량

}
