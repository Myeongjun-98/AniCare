package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrescriptiionSetDto {

    private Long clinicId;  // 저장할 진료기록 아이디
    private List<String> medicine;  // 약 이름
    private List<Integer> amount;   // 약 수량

}
