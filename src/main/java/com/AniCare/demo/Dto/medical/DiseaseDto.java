package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDto {

    private List<String> diseaseName;   // 저장할 병명
    private Long petId; // 반려동물 아이디

}
