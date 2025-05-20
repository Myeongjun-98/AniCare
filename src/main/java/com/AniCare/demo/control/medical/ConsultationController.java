package com.AniCare.demo.control.medical;

import com.AniCare.demo.Dto.medical.ClinicDiaryPetInfoDto;
import com.AniCare.demo.Dto.medical.ConsultationChatListDto;
import com.AniCare.demo.Dto.medical.UserConsultationListDto;
import com.AniCare.demo.entity.medical.Consultation;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.medical.VetRepository;
import com.AniCare.demo.service.mainpage.UserService;
import com.AniCare.demo.service.medical.ClinicDiaryService;
import com.AniCare.demo.service.medical.MedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/medical")
public class ConsultationController {

    private final MedicalService medicalService;
    private final UserService userService;
    private final ClinicDiaryService clinicDiaryService;
    private final UserRepository userRepository;
    private final VetRepository vetRepository;

    // 1:1상담하기 누를 시, 1:1상담 페이지로 이동
    @PostMapping("/consultation")
    public String startConsultation(
            @RequestParam Long vetInfoId,
            @RequestParam Long checkupId,
            Principal principal,
            Model model
    ) {
        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        // 채팅방 생성
        Consultation consultation = medicalService.createConsultation(principal.getName(), vetInfoId, checkupId);

        return "redirect:/medical/consultationRoom/" + consultation.getId();
    }

    // 상담페이지 이동
    @GetMapping("/consultationRoom/{roomId}")
    public String viewConsultation(@PathVariable Long roomId, Model model, Principal principal) {

        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        // 채팅들
        List<ConsultationChatListDto> dto = medicalService.loadMessages(roomId);

        // (유저)채팅방 정보
        UserConsultationListDto info = medicalService.roomInfo(roomId);

        //  로그인정보에서 유저 -> default(대표동물) 아이디로 pet정보 불러오기
        ClinicDiaryPetInfoDto pet = clinicDiaryService.petInfoDto(principal.getName());

        model.addAttribute("petInfo", pet);
        model.addAttribute("roomInfo", info);
        model.addAttribute("history", dto);
        return "medical/consultationRoom";
    }

    //상담페이지 - 채팅 저장 요청
    @PostMapping("/consultationRoom/send")
    public String chatSend(@RequestParam("content") String content,
                           @RequestParam("id") Long consultationId,
                           Principal principal, Model model) {

        String name = principal.getName();
        System.out.println("name : " + name);
        try {
            medicalService.saveChat(content, consultationId, name);
        } catch (Exception e) {
            model.addAttribute("chatError", "채팅 전송 실패");
            return "medical/consultationRoom";
        }

        return "redirect:/medical/consultationRoom/" + consultationId;
    }


}
