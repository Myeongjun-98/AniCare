package com.AniCare.demo.control.medical;

import com.AniCare.demo.DTO.medical.VetInfoDto;
import com.AniCare.demo.DTO.medical.VetInfoListDto;
import com.AniCare.demo.service.medical.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/medical")
public class ClinicController {
    @Autowired
    private VetService vetService;

    // 메디컬 진단받기 페이지
    @GetMapping("/vetList")
    public String checkupPage(Model model) {
        List<VetInfoListDto> vets = vetService.getVetsWithRatings();
        model.addAttribute("vets", vets);

        return "medical/vetList";
    }

    // 수의사 선택 시
    @GetMapping("/vetList/{vetInfoId}")
    public String clinicRequestPage(@PathVariable Long vetInfoId, Model model) {
        VetInfoDto vetInfoDto = vetService.findVetById(vetInfoId);
        model.addAttribute("vetSelect", vetInfoDto);
        return "medical/vetSelect";
    }

}
