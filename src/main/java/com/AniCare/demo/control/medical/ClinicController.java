package com.AniCare.demo.control.medical;

import com.AniCare.demo.Dto.medical.VetInfoListDto;
import com.AniCare.demo.service.medical.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClinicController {
    @Autowired
    private MedicalService medicalService;

    // 메디컬 진단받기 페이지
    @GetMapping("/medical/vetList")
    public String checkupPage(Model model) {
        List<VetInfoListDto> vets = medicalService.getVetsWithRatings();
        model.addAttribute("vets", vets);

        return "medical/vetList";
    }
}
