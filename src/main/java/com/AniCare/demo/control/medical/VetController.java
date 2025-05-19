package com.AniCare.demo.control.medical;

import com.AniCare.demo.Dto.admin.HospitalDto;
import com.AniCare.demo.Dto.medical.VetInfoDto;
import com.AniCare.demo.service.mainpage.UserService;
import com.AniCare.demo.service.medical.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/medical/vet")
@RequiredArgsConstructor
public class VetController {
    private final VetService vetService;
    private final UserService userService;

    @GetMapping("/vetPage")
    public String vetMainPage(Model model, Principal principal) {

        VetInfoDto vetInfoDto = vetService.getVetDefaultInfo(principal.getName());
        HospitalDto hospitalDto = vetService.getHospitalInfo(principal.getName());


        model.addAttribute("userDetailDto", userService.getVetDetail(principal.getName()));
        model.addAttribute("vetInfoDto", vetInfoDto);
        model.addAttribute("hospitalDto", hospitalDto);

        return "medical/vet/vetPage";
    }

}
