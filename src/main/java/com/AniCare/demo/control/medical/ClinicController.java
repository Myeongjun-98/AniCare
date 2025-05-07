package com.AniCare.demo.control.medical;

import com.AniCare.demo.service.medical.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClinicController {
    @Autowired
    private MedicalService medicalService;

    // 메디컬 진단받기 페이지
    @GetMapping("/medical/vetList")
    public String checkupPage(Model model) {

        model.addAttribute("vetList", medicalService.getAllVets());

        return "medical/vetList";
    }
}
