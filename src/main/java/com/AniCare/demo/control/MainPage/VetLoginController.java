package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.Dto.mainpage.VetLoginDto;
import com.AniCare.demo.Dto.mainpage.VetSignupDto;
import com.AniCare.demo.Dto.medical.VetInfoDto;
import com.AniCare.demo.repository.MainPage.VetLoginRepository;
import com.AniCare.demo.repository.MainPage.VetSignupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class VetLoginController {

    private final VetLoginRepository vetLoginRepository;
    private final VetSignupRepository vetSignupRepository;


    // 수의사 회원가입 페이지
    @GetMapping("vetsignup")
    public String vetsignup(Model model){
        model.addAttribute("vetsignup", new VetInfoDto());

        return "mainpage/vetsignup";

    }

    @PostMapping("vetsignup")
    public String vetsignup(@ModelAttribute("vet")VetSignupDto vetSignupDto,Long vetId) throws IOException {

        vetSignupRepository.findById(vetId);

        return "redirect:/mainpage/vetlogin";

    }




}

