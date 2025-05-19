package com.AniCare.demo.Dto.medical;

import com.AniCare.demo.constant.medical.PetStatus;
import com.AniCare.demo.entity.medical.Allergy;
import com.AniCare.demo.entity.medical.Disease;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClinicDiarySetDto {

    @NotNull(message = "날짜를 선택해주세요.")
    private LocalDate clinicDiaryRecordDate;    // 진료수첩 설정날짜
    @NotNull(message = "반려동물 상태를 선택해주세요.")
    private PetStatus status;                   // 반려동물 투병상태
    private List<Disease> petDisease;           // 반려동물 질병정보
    private List<Allergy> petAllergy;           // 반려동물 알러지 정보
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 50, message = "제목은 최대 50자까지 가능합니다.")
    private String boardTitle;                  // 진료수첩 제목
    private String boardContent;                // 진료수첩 내용
    private List<MultipartFile> files;      // 이미지파일 원본 이름
    private Long hospitalId;

}
