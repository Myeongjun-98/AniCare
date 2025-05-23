package com.AniCare.demo.Dto.medical;

import com.AniCare.demo.constant.medical.PetStatus;
import com.AniCare.demo.entity.admin.Hospital;
import com.AniCare.demo.entity.community.BoardFile;
import com.AniCare.demo.entity.medical.Allergy;
import com.AniCare.demo.entity.medical.Disease;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ClinicDiaryDto {

    private LocalDate clinicDiaryRecordDate;    // 진료수첩 설정날짜
    private PetStatus status;                   // 반려동물 투병상태
    private List<Disease> petDisease;           // 반려동물 질병정보
    private List<Allergy> petAllergy;           // 반려동물 알러지 정보
    private String boardTitle;                  // 진료수첩 제목
    private String boardContent;                // 진료수첩 내용
    private List<BoardFile> boardFiles;      // 이미지파일 원본 이름
    private int boardHit;                       // 진료수첩 조회수 불러오기
    private int boardLikeCount;                 // 진료수첩 좋아요 수
    private Hospital hospital;                  // 병원 정보 불러오기

}
