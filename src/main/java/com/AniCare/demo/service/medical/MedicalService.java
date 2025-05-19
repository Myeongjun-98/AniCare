package com.AniCare.demo.service.medical;

import com.AniCare.demo.Dto.medical.CheckupSetDto;
import com.AniCare.demo.Dto.medical.ConsultationChatListDto;
import com.AniCare.demo.Dto.medical.UserConsultationListDto;
import com.AniCare.demo.constant.MainPage.SenderType;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.medical.*;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.medical.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MedicalService {

    private final AllergyRepository allergyRepository;
    private final DiseaseRepository diseaseRepository;
    private final CheckupRepository checkupRepository;
    private final ConsultationRepository consultationRepository;
    private final ConsultationChatRepository consultationChatRepository;
    private final VetRepository vetRepository;
    // 로그인 관련 레포지토리
    private final UserRepository userRepository;
    private final mediPetRepository mediPetRepository;

    // --------------메서드

    // (임시) 로그인 유저 id로 반려동물 한 마리만 불러오기
    public Pet getOnePet(String userEmail) {
        return mediPetRepository.findByUserUserEmail(userEmail).stream().findFirst().orElseThrow(() -> new IllegalStateException("등록된 반려동물이 없습니다."));
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

    @Transactional
    // 문진표 정보 저장하기
    public Checkup saveCheckup(CheckupSetDto setDto, String userEmail) {

        // (임시) 로그인정보(userName)으로 대표동물 정보 불러오기
        Pet pet = getOnePet(userEmail);

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

    // 채팅방 정보 저장(채팅방 생성)
    @Transactional
    public Consultation createConsultation(String Email, Long vetInfoId, Long checkupId) {

        // ① User 조회
        User user = userRepository.findByUserEmail(Email)
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
    public List<UserConsultationListDto> loadChattingRooms(String userEmail) {
        List<UserConsultationListDto> dtos = consultationRepository.findMyConsultations(userEmail);

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
        //1. 채팅 내역 데이터 가져오기
        List<ConsultationChat> consultationChats = consultationChatRepository.findAllByConsultationId(roomId);

        List<ConsultationChatListDto> consultationChatListDtos = new ArrayList<>();

        for(ConsultationChat consultationChat : consultationChats) {

            //2. DTO 그릇에 데이터 차례로 담기
            ConsultationChatListDto consultationChatListDto = ConsultationChatListDto.from(consultationChat);

           if(consultationChat.getSenderUser() == null) {
                consultationChatListDto.setSenderType(SenderType.VET);
                consultationChatListDto.setSenderName(consultationChat.getSenderVet().getVetName());
           } else {
               consultationChatListDto.setSenderType(SenderType.USER);
               consultationChatListDto.setSenderName(consultationChat.getSenderUser().getUserName());
           }

           consultationChatListDtos.add(consultationChatListDto);
        }
        return consultationChatListDtos;
    }


    //채팅 저장
    public void saveChat(String content, Long consultationId, String name){

        //ConsultationChat 데이터 세팅
        ConsultationChat consultationChat = new ConsultationChat();
        Consultation consultation = consultationRepository.findById(consultationId).orElseThrow();

        consultationChat.setConsultation(consultation);
        consultationChat.setContent(content);
        consultationChat.setSendAt(LocalDateTime.now());
        consultationChat.setReadFlag(false);

        //보내는 사람 세팅 (user or vet)
        if(userRepository.findByUserEmail(name).isEmpty()) {
            VetInfo vetInfo = vetRepository.findByVetName(name);
            consultationChat.setSenderVet(vetInfo);
        }
        else {
            User user = userRepository.findByUserEmail(name).orElseThrow();
            consultationChat.setSenderUser(user);
        }

        consultationChatRepository.save(consultationChat);
    }
}
