package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleCalenderDto {

    private LocalDate scheduleDate; // 달력 날짜
    private int scheduleCount;  // 예약건수

}
