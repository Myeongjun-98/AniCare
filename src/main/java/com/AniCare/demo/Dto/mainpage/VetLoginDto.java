package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.entity.medical.VetInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VetLoginDto {

    private String vetId;
    private String vetPassword;

}
