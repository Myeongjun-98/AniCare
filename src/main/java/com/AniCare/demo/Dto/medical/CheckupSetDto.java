package com.AniCare.demo.DTO.medical;

import com.AniCare.demo.constant.medical.Symptom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckupSetDto {

    private Long petId; // 반려동물 정보
    private List<Symptom> symptom;  // 체크한 증상 리스트
    private String etcSymptom;  // 작성된 기타 증상

}
