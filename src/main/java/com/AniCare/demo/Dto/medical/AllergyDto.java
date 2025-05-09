package com.AniCare.demo.DTO.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllergyDto {

    private List<String> allergyName;   // 저장할 알러지명
    private Long petId;         // 반려동물 아이디

}
