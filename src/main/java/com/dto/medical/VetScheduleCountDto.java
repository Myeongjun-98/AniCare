package com.dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VetScheduleCountDto {

    private Long vetInfoId; // 수의사 식별용 아이디
    private int todayScheduleCount; // 오늘 예약/대기 건수

}
