package com.AniCare.demo.service.medical;

import com.AniCare.demo.DTO.medical.CheckupSetDto;
import com.AniCare.demo.DTO.medical.ClinicDiaryDto;
import com.AniCare.demo.DTO.medical.ClinicDiaryListDto;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.community.BoardFile;
import com.AniCare.demo.entity.medical.Allergy;
import com.AniCare.demo.entity.medical.Checkup;
import com.AniCare.demo.entity.medical.ClinicDiary;
import com.AniCare.demo.entity.medical.Disease;
import com.AniCare.demo.repository.community.BoardFileRepository;
import com.AniCare.demo.repository.medical.*;
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
    private final CheckupRepository checkupRepository;
    // 임시 로그인 관련 레포지토리
    private final devUserRepository devUserRepository;

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

    @Transactional
    // 문진표 정보 저장하기
    public Checkup saveCheckup(CheckupSetDto setDto, String userName) {

        // (임시) 로그인 된 유저 정보 불러오기
        User user = devUserRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalStateException("로그인 유저를 찾을 수 없습니다."));

        // 로그인된 유저의 대표동물 조회(임시)
        Pet pet = user.getDefaultPet();
        if (pet == null) {
            throw new IllegalStateException("대표 반려동물이 설정되어 있지 않습니다.");
        }

        // Checkup 엔티티에 " 증상 리스트 " , " 기타 증상 문자열 " 세팅
        Checkup c = new Checkup();
        c.setPet(pet);
        c.setSymptom(setDto.getSymptom());
        c.setEtcSymptom(setDto.getEtcSymptom());

        // 확인용..
        System.out.println("문진표 정보 확인 : " + c.getSymptom());

        // 저장
        return checkupRepository.save(c);
    }

}