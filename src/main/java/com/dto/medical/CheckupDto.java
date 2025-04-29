package com.dto.medical;

import com.constant.medical.Symptom;
import com.entity.MainPage.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class CheckupDto {

    private Long petId;    // 반려동물 정보
    private List<Symptom> symptom;  // 체크한 증상 리스트
    private String etcSymptom;  // 작성된 기타 증상

}
