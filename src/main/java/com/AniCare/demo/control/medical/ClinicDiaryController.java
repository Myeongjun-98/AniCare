package com.AniCare.demo.control.medical;

import com.AniCare.demo.service.medical.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/medical")
public class ClinicDiaryController {

    @Autowired
    private MedicalService medicalService;

    // 05/09 진료수첩 리스트, Principal로 user정보 받아와 user에서 pet 꺼내와야함. userService필요
//    레포지토리, 서비스는 만듦(진료수첩 리스트 dto)
    @GetMapping("/clinicdiary")
    public String clinicDiaryPage(Principal principal,
                                  @PathVariable Long petId, Model model) {

        String username = principal.getName();

        return "medical/clinicdiary";
    }


}
