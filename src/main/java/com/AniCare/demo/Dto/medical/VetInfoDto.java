package com.AniCare.demo.Dto.medical;

import com.AniCare.demo.constant.medical.PetSpecies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VetInfoDto {

    private Long vetInfoId; // 수의사 고유아이디
    private String profileImage;    // 수의사 이미지
    private String vetName; // 수의사 이름
    private String hospitalName;    // 수의사 소속 병원
    private List<PetSpecies> curingCapable; // 진료가능 동물종 리스트
    private LocalTime onWorkTime;   // 근무시간(시작)
    private LocalTime offWorkTime;  // 근무시간(끝)
    private double averageRating;   // 별점
}
