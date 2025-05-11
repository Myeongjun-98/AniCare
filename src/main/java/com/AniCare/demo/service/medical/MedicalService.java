package com.AniCare.demo.service.medical;

import com.AniCare.demo.DTO.medical.CheckupSetDto;
import com.AniCare.demo.DTO.medical.ClinicDiaryDto;
import com.AniCare.demo.DTO.medical.ClinicDiaryListDto;
import com.AniCare.demo.DTO.medical.ClinicDiaryPetInfoDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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

    // --------------메서드

    // 로그인 된 유저 정보로 대표동물 정보 불러오기
    public Pet getDefaultPetFromUserName(String userName) {

        // (임시) 로그인 된 유저 정보 불러오기
        User user = devUserRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalStateException("로그인 유저를 찾을 수 없습니다."));

        // 로그인된 유저의 대표동물 조회(임시)

        if (user.getDefaultPet() == null) {
            throw new IllegalStateException("대표 반려동물이 설정되어 있지 않습니다.");
        }
        return user.getDefaultPet();
    }

    // ----동물의 id로 질병/알러지 정보 불러오기
    // 질병리스트
    public List<Disease> getDiseases(Long petId) {
        return diseaseRepository.findByPet_id(petId);
    }

    // 알러지리스트
    public List<Allergy> getAllergies(Long petId) {
        return allergyRepository.findByPet_id(petId);
    }
    // ----

    // --------------메서드

    // ----------------------------------------------------- 진료수첩

    // (임시)로그인 정보로 진료수첩 출력용 clinicDiaryPetInfoDto 정보 담기
    public ClinicDiaryPetInfoDto petInfoDto(String userName) {
        Pet pet = getDefaultPetFromUserName(userName);
        return new ClinicDiaryPetInfoDto(
                pet.getPetImage(),
                pet.getPetName(),
                pet.getPetBreed(),
                pet.getPetSex(),
                pet.getPetAge(),
                pet.getPetSpecies(),
                getDiseases(pet.getId()),
                getAllergies(pet.getId())
        );
    }

    // 반려동물 정보로 진료수첩 리스트 페이징으로 불러오기
    @Transactional(readOnly = true)
    public Page<ClinicDiaryListDto> findClinicDiariesByPetId(
            Long petId,
            int page,    // 0-based
            int size
    ) {
        return clinicDiaryRepository.findAllByPetId(petId, PageRequest.of(page, size));
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

    // ------------------------------------------------------- 진료수첩

    @Transactional
    // 문진표 정보 저장하기
    public Checkup saveCheckup(CheckupSetDto setDto, String userName) {

        // (임시) 로그인정보(userName)으로 대표동물 정보 불러오기
        Pet pet = getDefaultPetFromUserName(userName);

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