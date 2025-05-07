package com.control.medical;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicalMainController {

    // 메디컬 메인페이지
    @GetMapping("/medical/medicalMain")
    public String medicalMainPage(Model model) {
        return "medical/medicalMain";
    }

    // 메디컬 진단받기 페이지
    @GetMapping("/medical/checkupPage")
    public String checkupPage(Model model) {
        return "medical/checkupPage";
    }

    // 메디컬 수의사페이지
    @GetMapping("/medical/vet/vetPage")
    public String vetPage(Model model) {
        return "medical/vet/vetPage";
    }

    // 개발용 로그인
    @GetMapping("/loginmedi")
    public String login() {
        return "medical/loginmedi";
    }


}
