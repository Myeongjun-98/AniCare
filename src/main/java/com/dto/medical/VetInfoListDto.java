package com.Dto.medical;

import com.constant.medical.PetSpecies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VetInfoListDto {

    private Long vetInfoId; // 수의사 정보 아이디
    private String vetName; // 수의사 이름
    private String hospitalName;    // 병원 이름
    private String profileImage;    // 프로필 이미지
    private List<PetSpecies> curingCapable; // 진료 가능 동물종 리스트

}
