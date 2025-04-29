package com.dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class VetSignUpDto {

    private String vetId;   // 수의사 아이디
    private String vetName; // 수의사 이름
    private String vetPassword; // 수의사 비밀번호

}
