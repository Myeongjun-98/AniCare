package com.dto.medical;

import com.constant.medical.PetStatus;
import com.entity.medical.Allergy;
import com.entity.medical.Disease;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter@NoArgsConstructor@AllArgsConstructor@Getter
public class ClinicDiarySetDto {

    private LocalDate clinicDiaryRecordDate;    // 진료수첩 설정날짜
    private PetStatus status;                   // 반려동물 투병상태
    private List<Disease> petDisease;           // 반려동물 질병정보
    private List<Allergy> petAllergy;           // 반려동물 알러지 정보
    private String boardTitle;                  // 진료수첩 제목
    private String boardContent;                // 진료수첩 내용
    private List<String> fileOriginalName;      // 이미지파일 원본 이름

}
