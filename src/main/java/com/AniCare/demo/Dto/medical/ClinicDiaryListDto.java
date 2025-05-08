package com.AniCare.demo.DTO.medical;

import com.AniCare.demo.constant.medical.PetStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ClinicDiaryListDto {

    private Long clinicDiaryId; // 진료수첩 식별용 아이디
    private LocalDate boardWriteDate;   // LocalDateTime -> LocalDate , 작성일
    private String boardTitle;  // 게시글 제목
    private int boardHit;       // 게시글 조회수
    private PetStatus status;   // 반려동물 병 상태

}
