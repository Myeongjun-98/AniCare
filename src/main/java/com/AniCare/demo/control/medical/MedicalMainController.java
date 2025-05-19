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

    @GetMapping("/medical/medicalMain")
    public String medicalMainPage(Model model, Principal principal) {

        // 헤더에 사용자 정보 띄우기
        if (principal != null) {
            boolean hasVet = userService.isVetLogin(principal.getName());
            if (hasVet) {
                model.addAttribute("userDetailDto", userService.getVetDetail(principal.getName()));
            } else
                model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        return "medical/medicalMain";
    }

}
