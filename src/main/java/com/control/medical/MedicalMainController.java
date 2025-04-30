package com.control.medical;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicalMainController {

    @GetMapping("/medical/medicalMain")
    public String medicalMainPage(Model model){
        return "medical/medicalMain";
    }

    @GetMapping("/medical/checkupPage")
    public String checkupPage(Model model){
        return "medical/checkupPage";
    }

    @GetMapping("/medical/vet/vetPage")
    public String vetPage(Model model){
        return "medical/vet/vetPage";
    }




}
