package com.dto.medical;

import com.entity.medical.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class VetScheduleListDto {

    private Schedule scheduleId;    // 스케쥴 아이디
    private LocalDateTime scheduleTime; // 예약 시간
    private String userName;    // 회원 이름
    private String petName;     // 반려동물 이름
    private Long checkupId;     // 문진표 아이디

}
