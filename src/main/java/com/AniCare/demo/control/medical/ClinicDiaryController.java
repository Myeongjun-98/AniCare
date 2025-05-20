package com.AniCare.demo.control.medical;

import com.AniCare.demo.Dto.admin.HospitalDto;
import com.AniCare.demo.Dto.medical.ClinicDiaryListDto;
import com.AniCare.demo.Dto.medical.ClinicDiaryPetInfoDto;
import com.AniCare.demo.Dto.medical.ClinicDiarySetDto;
import com.AniCare.demo.service.adminService.HospitalService;
import com.AniCare.demo.service.mainpage.UserService;
import com.AniCare.demo.service.medical.ClinicDiaryService;
import com.AniCare.demo.service.medical.MedicalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/medical")
@RequiredArgsConstructor
public class ClinicDiaryController {

    private final MedicalService medicalService;
    private final ClinicDiaryService clinicDiaryService;
    private final UserService userService;
    private final HospitalService hospitalService;

    // 진료수첩 페이지(특정 반려동물의 메인 진료수첩 페이지)
    @GetMapping("/clinicdiary")
    public String clinicDiaryPage(Principal principal,
                                  Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page) {

        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        String userEmail = principal.getName();

        //  로그인정보에서 유저 -> default(대표동물) 아이디로 pet정보 불러오기
        ClinicDiaryPetInfoDto pet = clinicDiaryService.petInfoDto(principal.getName());
        // 2) 진료수첩 페이징 리스트 (page 사이즈는 10개로 설정)
        Page<ClinicDiaryListDto> diaryPage =
                clinicDiaryService.findClinicDiariesByPetId(
                        medicalService.getOnePet(userEmail).getId(),
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
    public String viewClinicDiary(@PathVariable Long clinicDiaryId, Model model, Principal principal) {

        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        //  로그인정보에서 유저 -> default(대표동물) 아이디로 pet정보 불러오기
        ClinicDiaryPetInfoDto pet = clinicDiaryService.petInfoDto(principal.getName());

        model.addAttribute("petInfo", pet);
        model.addAttribute("clinicDiaryDto", clinicDiaryService.viewClinicDiaryDetail(clinicDiaryId));

        return "medical/clinicdiaryDetail";
    }

    // 진료수첩 작성페이지
    @GetMapping("clinicdiary/new")
    public String clinicDiarySavingPage(Model model, Principal principal) {

        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        // 비어있는 ClinicDiarySetDto 객체 보내기
        ClinicDiarySetDto dto = new ClinicDiarySetDto();
        // 병원 리스트 보내기
        List<HospitalDto> hospitals = hospitalService.findAll();

        model.addAttribute("clinicDiarySetDto", dto);
        model.addAttribute("hospitals", hospitals);
        return "medical/newClinicDiary";
    }

    // 진료수첩 작성 요청
    @PostMapping("uploadClinicDiary")
    public String clinicDiarySave(Model model, @Valid ClinicDiarySetDto clinicDiarySetDto,
                                  BindingResult bindingResult,
                                  Principal principal) {

        //오류가 나서 페이지 반환되어도 hospital 목록 다시 보여줄 수 있게 model 선언해주기
        model.addAttribute("hospitals", hospitalService.findAll());

        // 헤더에 사용자 정보 띄우기??
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        String email = principal.getName();

        if (bindingResult.hasErrors()) {
            return "medical/newClinicDiary";
        }

        try {
            clinicDiaryService.clinicDiarySave(clinicDiarySetDto, clinicDiaryService.getOnePet(email).getId(), email);
        } catch (Exception e) {
            model.addAttribute("clinicDiaryUploadError", "진료수첩 작성 실패");
            return "medical/newClinicDiary";
        }

        return "redirect:/medical/clinicdiary";
    }


}
