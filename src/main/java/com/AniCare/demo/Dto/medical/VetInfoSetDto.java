package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VetInfoSetDto {

    private Long vetInfoId;     // 수의사 정보 아이디(노출 안됨)
    private String vetId;       // 수의사 아이디  (수정 X)
    private String vetName;     // 수의사 이름   (수정 가능)
    private Long hospitalId;    // 병원 아이디   (수정 가능)
    private String profileImage;    // 프로필 이미지 (수정 가능)
    private LocalTime onWorkTime;   // 근무시간(시작) (수정 가능)
    private LocalTime offWorkTime;  // 근무시간(끝) (수정 가능)

}
