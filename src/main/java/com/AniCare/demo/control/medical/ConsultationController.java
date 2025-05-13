package com.AniCare.demo.control.medical;

import com.AniCare.demo.entity.medical.Consultation;
import com.AniCare.demo.service.medical.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/medical")
public class ConsultationController {

    @Autowired
    private MedicalService medicalService;

    // 1:1상담하기 누를 시, 1:1상담 페이지로 이동
    @PostMapping("/consultation")
    public String startConsultation(
            @RequestParam Long vetInfoId,
            @RequestParam Long checkupId,
            Principal principal
    ) {
        Consultation consultation = medicalService.createConsultation(principal.getName(), vetInfoId, checkupId);
        return "redirect:/medical/consultation/" + consultation.getId();
    }

    //
    @GetMapping("/consultation/{consultationId}")
    public String viewConsultation(@PathVariable Long consultationId, Model model) {


        return "medical/consultationRoom";
    }

}
