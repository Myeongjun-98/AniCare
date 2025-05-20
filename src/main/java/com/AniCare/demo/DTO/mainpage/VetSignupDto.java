package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.entity.medical.VetInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class VetSignupDto {
    private String vetId;
    private String password;
    private String vetName;
    private LocalTime startTime; // 진료시작 시간
    private LocalTime finTime; // 진료종료 시간



}
