package com.dto.medical;

import com.entity.medical.Checkup;
import com.entity.medical.VetInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter
public class UserScheduleSetDto {

    private VetInfo vetInfoId;     //선택한 수의사 고유 아이디
    private LocalDateTime requestDate;  // (선택) 예약 시간
    private Checkup checkupId;     // 저장되는 문진표 아이디
    private LocalDateTime scheduleTime; // 실제로 진료 요청한 시간(  now()  )
    private Boolean isScheduleProcessed;    // 진료 처리됨 유무

}
