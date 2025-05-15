package com.AniCare.demo.Dto.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserConsultationListDto {
    private Long consultationId;       // 상담방 고유 ID
    private String userName;           // 일반 유저 이름
    private String petName;            // 해당 반려동물 이름
    private String vetName;            // 수의사 이름
    private boolean existUnreadMessage = false; // 안읽은 메시지 있는지 여부
}
