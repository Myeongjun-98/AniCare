package com.AniCare.demo.service.medical;

import com.AniCare.demo.Dto.medical.*;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.community.BoardFile;
import com.AniCare.demo.entity.medical.*;
import com.AniCare.demo.repository.community.BoardFileRepository;
import com.AniCare.demo.repository.medical.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final ConsultationRepository consultationRepository;
    private final ConsultationChatRepository consultationChatRepository;
    private final VetRepository vetRepository;
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

    @Transactional
    public Consultation createConsultation(String name, Long vetInfoId, Long checkupId) {

        // ① User 조회
        User user = devUserRepository.findByUserName(name)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // ② VetInfo 조회
        VetInfo vet = vetRepository.findById(vetInfoId)
                .orElseThrow(() -> new NoSuchElementException("Vet not found"));

        // ③ Checkup (문진표) 조회
        Checkup checkup = checkupRepository.findById(checkupId)
                .orElseThrow(() -> new NoSuchElementException("Checkup not found"));

        // ④ Consultation 엔티티에 모두 연결해서 저장
        Consultation con = new Consultation();
        con.setUser(user);
        con.setVet(vet);
        con.setCheckup(checkup);
        con.setStartedAt(LocalDateTime.now());
        con.setIsEnd(false);
        return consultationRepository.save(con);
    }

    // 채팅방리스트(여러 개) 불러오기    필요한가??
    public List<UserConsultationListDto> loadChattingRooms(String userName) {
        List<UserConsultationListDto> dtos = consultationRepository.findMyConsultations(userName);

        // 각 방마다 안읽은 메시지가 있는지 체크
        dtos.forEach(dto -> {
            boolean hasUnread = consultationChatRepository
                    .existsByConsultationIdAndReadFlagFalse(dto.getConsultationId());
            dto.setExistUnreadMessage(hasUnread);
        });

        return dtos;
    }

    // 채팅방정보(한 개) 불러오기
    public UserConsultationListDto roomInfo(Long roomId) {
        Consultation c = consultationRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException(roomId + "를 찾을 수 없습니다."));
        UserConsultationListDto u = new UserConsultationListDto();

        u.setConsultationId(c.getId());
        u.setUserName(c.getUser().getUserName());
        u.setPetName(c.getCheckup().getPet().getPetName());
        u.setVetName(c.getVet().getVetName());

        // 안읽은 메시지 있는지 조회
        boolean hasUnread = consultationChatRepository.existsByConsultation_IdAndReadFlagFalse(roomId);

        u.setExistUnreadMessage(hasUnread);
        return u;
    }

    // 채팅로그 불러오기
    public List<ConsultationChatListDto> loadMessages(Long roomId) {
        List<ConsultationChatListDto> dtos = consultationChatRepository.findMessagesByConsultation(roomId);
        return dtos;
    }
}
