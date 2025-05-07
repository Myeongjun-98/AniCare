package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class DiagnosisInfoSetDto {

    private List<String> diseaseName;   // 병명
    private List<String> allergyName;   // 알러지명

}
