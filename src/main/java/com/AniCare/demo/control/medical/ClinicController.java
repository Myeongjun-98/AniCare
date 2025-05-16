package com.AniCare.demo.control.medical;

import com.AniCare.demo.Dto.medical.CheckupSetDto;
import com.AniCare.demo.Dto.medical.VetInfoDto;
import com.AniCare.demo.Dto.medical.VetInfoListDto;
import com.AniCare.demo.constant.medical.PetSpecies;
import com.AniCare.demo.entity.medical.Checkup;
import com.AniCare.demo.service.mainpage.UserService;
import com.AniCare.demo.service.medical.MedicalService;
import com.AniCare.demo.service.medical.VetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/medical")
public class ClinicController {

    private final MedicalService medicalService;
    private final VetService vetService;
    private final UserService userService;

    // 메디컬 진단받기 페이지
    @GetMapping("/checkupPage")
    public String checkupPage(Model model, Principal principal) {

        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        // 객체를 담을 껍데기 CheckSetDto를 담아서 페이지로 반환
        model.addAttribute("checkupSetDto", new CheckupSetDto());

        return "medical/checkupPage";
    }

    // 메디컬 진단받기 페이지에서 문진표 제출
    @PostMapping("/checkupPage")
    public String submitCheckup(
            Principal principal,
            Model model,
            @ModelAttribute CheckupSetDto checkupSetDto
    ) {

        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        Checkup saved = medicalService.saveCheckup(checkupSetDto, principal.getName());

        // petSpecies 기준으로 vetList로 리다이렉트
        String species = saved.getPet().getPetSpecies().name();

        // 문진표 정보 담아서 리다이렉트
        Long checkupId = saved.getId();

        return "redirect:/medical/vetList?species=" + species + "&checkupId=" + checkupId;
    }

    // 메디컬 진단받기 페이지 이후 수의사 리스트 불러오기
    @GetMapping("/vetList")
    public String vetListViewPage(@RequestParam(required = false) String species,
                                  @RequestParam(required = false) Long checkupId,
                                  Principal principal,
                                  Model model) {

        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        List<VetInfoListDto> vets;

        // (임시)User에서 대표동물이 있을 시, 동물종에 일치하는 수의사 먼저 노출
        if (species != null) {
            PetSpecies petSpecies = PetSpecies.valueOf(species);
            vets = vetService.getVetsBySpeciesFirst(petSpecies);

        }
        // 그렇지 않을 시 그냥 별점순으로 정렬
        else {
            vets = vetService.getVetsWithRatings();
        }

        model.addAttribute("vets", vets);
        model.addAttribute("checkupId", checkupId);

        return "medical/vetList";
    }

    // 수의사 선택 시
    @GetMapping("/vetList/{vetInfoId}")
    public String clinicRequestPage(@PathVariable Long vetInfoId,
                                    @RequestParam Long checkupId,
                                    Model model,
                                    Principal principal) {

        // 헤더에 사용자 정보 띄우기
        if (principal.getName() != null) {
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));
        }

        VetInfoDto vetInfoDto = vetService.findVetById(vetInfoId);

        model.addAttribute("vetSelect", vetInfoDto);
        model.addAttribute("checkupId", checkupId);

        return "medical/vetSelect";
    }

}
