package com.AniCare.demo.control.medical;

import com.AniCare.demo.Dto.medical.ClinicDiaryListDto;
import com.AniCare.demo.Dto.medical.ClinicDiaryPetInfoDto;
import com.AniCare.demo.service.medical.MedicalService;
import com.AniCare.demo.service.medical.devUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/medical")
public class ClinicDiaryController {

    @Autowired
    private MedicalService medicalService;
    @Autowired
    private devUserService devUserService;

    // 진료수첩 페이지(특정 반려동물의 메인 진료수첩 페이지)
    @GetMapping("/clinicdiary")
    public String clinicDiaryPage(Principal principal,
                                  Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        String userName = principal.getName();

        //  로그인정보에서 유저 -> default(대표동물) 아이디로 pet정보 불러오기
        ClinicDiaryPetInfoDto pet = medicalService.petInfoDto(principal.getName());
        // 2) 진료수첩 페이징 리스트 (page 사이즈는 10개로 설정)
        Page<ClinicDiaryListDto> diaryPage =
                medicalService.findClinicDiariesByPetId(
                        medicalService.getDefaultPetFromUserName(userName).getId(),
                        page,
                        10
                );
//todo later => 펫 슬라이드 정보 불러오기(미구현)

        // 반려동물 정보, 반려동물에 종속된 진료수첩 리스트 담기
        model.addAttribute("petInfo", pet);
        model.addAttribute("clinicDiaryList", diaryPage);

        return "medical/clinicdiary";
    }

    // 진료수첩 상세페이지
    @GetMapping("clinicdiary/{clinicDiaryId}")
    public String viewClinicDiary(@PathVariable Long clinicDiaryId, Model model) {

        model.addAttribute("clinicDiaryDto", medicalService.viewClinicDiaryDetail(clinicDiaryId));

        return "medical/clinicdiaryDetail";
    }


}
