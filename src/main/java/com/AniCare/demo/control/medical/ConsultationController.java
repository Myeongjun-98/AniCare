package com.AniCare.demo.control.medical;

import com.AniCare.demo.Dto.medical.ConsultationChatListDto;
import com.AniCare.demo.Dto.medical.UserConsultationListDto;
import com.AniCare.demo.entity.medical.Consultation;
import com.AniCare.demo.service.mainpage.UserService;
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

        model.addAttribute("roomInfo", info);
        model.addAttribute("history", dto);
        return "medical/consultationRoom";
    }


}
