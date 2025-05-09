package com.AniCare.demo.service.medical;

import com.AniCare.demo.DTO.medical.ClinicDiaryDto;
import com.AniCare.demo.DTO.medical.ClinicDiaryListDto;
import com.AniCare.demo.entity.community.BoardFile;
import com.AniCare.demo.entity.medical.Allergy;
import com.AniCare.demo.entity.medical.ClinicDiary;
import com.AniCare.demo.entity.medical.Disease;
import com.AniCare.demo.repository.community.BoardFileRepository;
import com.AniCare.demo.repository.medical.AllergyRepository;
import com.AniCare.demo.repository.medical.ClinicDiaryRepository;
import com.AniCare.demo.repository.medical.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalService {

    private final ClinicDiaryRepository clinicDiaryRepository;
    private final AllergyRepository allergyRepository;
    private final DiseaseRepository diseaseRepository;
    private final BoardFileRepository boardFileRepository;

    @Transactional(readOnly = true)
    public List<ClinicDiaryListDto> findClinicDiariesByPetId(Long petId) {

        return clinicDiaryRepository.findAllByPetId(petId).stream()
                .map(
                        c -> new ClinicDiaryListDto(
                                c.getId(),
                                c.getBoard().getBoardWriteDate(),
                                c.getBoard().getBoardTitle(),
                                c.getBoard().getBoardHit(),
                                c.getStatus()
                        )
                ).collect(Collectors.toList());
    }

    // 진료수첩 상세페이지 dto값 전달(출력)
    @Transactional(readOnly = true)
    public ClinicDiaryDto viewClinicDiaryDetail(Long id) {
        ClinicDiary c = clinicDiaryRepository.findByClinicDiaryId(id).orElseThrow(() -> new NoSuchElementException(id + "를 찾을 수 없습니다."));

        List<Disease> diseases = diseaseRepository.findByPet_id(c.getPet().getId());
        List<Allergy> allergies = allergyRepository.findByPet_id(c.getPet().getId());
        List<BoardFile> boardFiles = boardFileRepository.findAllByBoardId(c.getBoard().getId());

        return new ClinicDiaryDto(
                c.getClinicDiaryRecordDate().toLocalDate(),
                c.getStatus(),
                diseases,
                allergies,
                c.getBoard().getBoardTitle(),
                c.getBoard().getBoardContent(),
                boardFiles,
                c.getBoard().getBoardHit(),
                c.getBoard().getLikeCount(),
                c.getHospital()
        );
    }


}