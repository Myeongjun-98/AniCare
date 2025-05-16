package com.AniCare.demo.control.medical;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/medical/vet")
public class VetController {
    @GetMapping("/vetPage")
    public String vetMainPage(Model model) {
        return "/medical/vet/vetPage";
    }
}
