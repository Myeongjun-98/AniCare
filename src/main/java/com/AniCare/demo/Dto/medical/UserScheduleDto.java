package com.AniCare.demo.DTO.medical;

import com.AniCare.demo.entity.medical.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserScheduleDto {

    private Schedule scheduleId;    // 스케쥴 아이디
    private Long checkupId;     // 스케쥴 식별용 문진표 아이디

}
