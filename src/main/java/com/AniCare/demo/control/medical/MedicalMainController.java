package com.AniCare.demo.control.medical;

import com.AniCare.demo.service.mainpage.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MedicalMainController {

    private final UserService userService;

    // 메디컬 메인페이지
    @GetMapping("/medical/medicalMain")
    public String medicalMainPage(Model model, Principal principal) {

            // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        return "medical/medicalMain";
    }

    // 메디컬 수의사페이지
    @GetMapping("/medical/vet/vetPage")
    public String vetPage(Model model) {
        return "medical/vet/vetPage";
    }


}
