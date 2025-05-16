package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.VetLoginDto;
import com.AniCare.demo.Dto.mainpage.VetSignupDto;
import com.AniCare.demo.entity.medical.VetInfo;
import com.AniCare.demo.repository.MainPage.VetLoginRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VetLoginService {
    private final VetLoginRepository vetLoginRepository;


    // 수의사 회원가입
    public void vetSignUp(@Valid VetSignupDto vetSignupDto, PasswordEncoder passwordEncoder){

        VetInfo vetInfo = VetInfo.createVet(vetSignupDto,passwordEncoder);

        vetLoginRepository.save(vetInfo);

    }


    // 수의사 로그인





}
