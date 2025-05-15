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


    public VetInfo to (VetSignupDto vetSignupDto){
        VetInfo vetInfo = new VetInfo();

        vetInfo.setVetId(vetSignupDto.getVetId());
        vetInfo.setVetPassword(vetSignupDto.getPassword());
        vetInfo.setVetName(vetSignupDto.getVetName());
        vetInfo.setOnWorkTime(vetSignupDto.getStartTime());
        vetInfo.setOffWorkTime(vetSignupDto.getFinTime());

        return vetInfo;
    }
}
