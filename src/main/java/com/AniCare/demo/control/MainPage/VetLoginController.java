package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.Dto.mainpage.VetLoginDto;
import com.AniCare.demo.Dto.mainpage.VetSignupDto;
import com.AniCare.demo.Dto.medical.VetInfoDto;
import com.AniCare.demo.entity.medical.VetInfo;
import com.AniCare.demo.repository.MainPage.VetLoginRepository;
import com.AniCare.demo.repository.MainPage.VetSignupRepository;
import com.AniCare.demo.service.mainpage.VetLoginService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class VetLoginController {

    private final VetLoginService vetLoginService;
    private final PasswordEncoder passwordEncoder;


    // 수의사 회원가입 페이지
    @GetMapping("/mainpage/vetsignup")
    public String vetsignup(Model model){

        model.addAttribute("vetSignupDto", new VetSignupDto());

        return "mainpage/vetsignup";

    }

    @PostMapping("/mainpage/vetsignup")
    public String vetsignup(@ModelAttribute("vetSignupDto")VetSignupDto vetSignupDto,String vetId) throws IOException {

        vetLoginService.vetSignUp(vetSignupDto,passwordEncoder);


        return "redirect:/mainpage/vetsignup";

    }

    // 수의사 로그인 페이지
    @GetMapping("/vetlogin")
    public String vetLoginPage(Model model){

        return "mainpage/vetlogin";
    }

    @GetMapping("/mainpage/vetlogin")
    public String vetLogin(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception, Model model){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);


        return "mainpage/vetlogin";
    }



}

