package com.AniCare.demo.DTO.medical;

import com.AniCare.demo.constant.medical.PetStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
public class ClinicDiaryListDto {

    private Long clinicDiaryId; // 진료수첩 식별용 아이디
    private LocalDate recordDate;   // LocalDateTime -> LocalDate , 날짜(설정일)
    private String boardTitle;  // 게시글 제목
    private Integer boardHit;       // 게시글 조회수
    private PetStatus status;   // 반려동물 병 상태

    // JPQL Projection 용 생성자: 2번째 파라미터는 LocalDateTime
    public ClinicDiaryListDto(
            Long clinicDiaryId,
            LocalDateTime fullDateTime,
            String boardTitle,
            Integer boardHit,
            PetStatus status
    ) {
        this.clinicDiaryId = clinicDiaryId;
        this.recordDate = fullDateTime.toLocalDate();  // 여기서 Date 변환
        this.boardTitle = boardTitle;
        this.boardHit = boardHit;
        this.status = status;
    }
}
