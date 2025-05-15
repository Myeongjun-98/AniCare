package com.AniCare.demo.control.admin;

import com.AniCare.demo.constant.MainPage.Authorization;
import com.AniCare.demo.Dto.admin.*;
import com.AniCare.demo.Dto.mainpage.UserDetailDto;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.medical.VetInfo;
import com.AniCare.demo.repository.medical.VetRepository;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.service.adminService.*;
import com.AniCare.demo.service.mainpage.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminPageController {

    private final VetRepository vetRepository;
    private final EnquiryService enquiryService;
    private final HospitalService hospitalService;
    private final MasterAccountService masterAccountService;
    private final NoticeService noticeService;
    private final ReportListService reportListService;
    private final UserService userService;
    private final UserRepository userRepository;

    // ⭐ 로그인된 사용자 정보 공통 등록 메서드
    private void addUserInfoToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            String email = auth.getName();
            User user = userRepository.findByUserEmail(email).orElse(null);
            if (user != null) {
                UserDetailDto userDetailDto = UserDetailDto.from(user);
                model.addAttribute("userDetailDto", userDetailDto);
            }
        }
    }

    @GetMapping("/ad/hospital")
    public String hospitalPage(Model model) {
        addUserInfoToModel(model);
        List<HospitalDto> hospitalList = hospitalService.findAll();
        List<VetInfo> vetList = vetRepository.findAll();
        model.addAttribute("hospitals", hospitalList);
        model.addAttribute("hospital", new HospitalDto());
        model.addAttribute("vetList", vetList);
        return "ad/hospital";
    }

    @GetMapping("/ad/hospitalList")
    public String hospitalList(Model model) {
        addUserInfoToModel(model);
        List<HospitalDto> hospitalList = hospitalService.findAll();
        model.addAttribute("hospitals", hospitalList);
        return "ad/hospitalList";
    }

    @PostMapping("/ad/hospital")
    public String registerHospital(@ModelAttribute HospitalDto hospitalDto) {
        hospitalService.save(hospitalDto);
        return "redirect:/ad/hospital?success=true";
    }

    @GetMapping("/ad/enquiryReply")
    public String enquiryReplyPage(Model model) {
        addUserInfoToModel(model);
        List<EnquiryReplyViewDto> enquiries = enquiryService.findAll();
        model.addAttribute("enquiries", enquiries);
        return "ad/enquiryReply";
    }

    @PostMapping("/ad/enquiryReply")
    public String replyToEnquiry(@RequestParam("id") Long enquiryId,
                                 @RequestParam("reply") String reply) {
        enquiryService.reply(enquiryId, reply);
        return "redirect:/ad/enquiryReply";
    }

    @GetMapping("/ad/masterAd")
    public String masterAdPage(Model model) {
        addUserInfoToModel(model);
        List<MasterAccountDto> accounts = masterAccountService.findAll();
        model.addAttribute("userList", accounts);
        return "ad/masterAd";
    }

    @PostMapping("/ad/masterAd/role/update")
    public String updateUserRole(@RequestParam Long id,
                                 @RequestParam Authorization role) {
        masterAccountService.updateRole(id, role);
        return "redirect:/ad/masterAd";
    }

    @GetMapping("/ad/notice")
    public String noticePage(Model model) {
        addUserInfoToModel(model);
        List<NoticeListDto> notices = noticeService.getNoticeList();
        model.addAttribute("notices", notices);
        return "ad/notice";
    }

    @PostMapping("/notice/new")
    public String saveNotice(@ModelAttribute NoticeDto noticeDto){
        noticeService.save(noticeDto);
        return "redirect:/ad/notice";
    }

    @PostMapping("/notice/update")
    public String updateNotice(@ModelAttribute NoticeDto noticeDto) {
        noticeService.update(noticeDto.getId(), noticeDto);
        return "redirect:/ad/notice";
    }

    @PostMapping("/notice/{id}/delete")
    public String deleteNotice(@PathVariable Long id) {
        noticeService.delete(id);
        return "redirect:/ad/notice?delete=true";
    }

    @GetMapping("/ad")
    public String adHomePage(Model model) {
        addUserInfoToModel(model);
        return "ad/ad";
    }

    @GetMapping("/ad/form")
    public String formPage(Model model) {
        addUserInfoToModel(model);
        return "ad/form";
    }

    @GetMapping("/ad/hospital/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        addUserInfoToModel(model);
        HospitalDto hospitalDto = hospitalService.findById(id);
        model.addAttribute("hospitalDto", hospitalDto);
        return "ad/hospitalEdit";
    }

    @PostMapping("/ad/hospital/delete/{id}")
    public String deleteHospital(@PathVariable Long id) {
        hospitalService.delete(id);
        return "redirect:/ad/hospitalList?delete=true";
    }

    @PostMapping("/ad/hospital/edit")
    public String updateHospital(@ModelAttribute HospitalDto dto) {
        hospitalService.update(dto);
        return "redirect:/ad/hospitalList";
    }

    @GetMapping("/ad/reportList")
    public String reportListPage(Model model) {
        addUserInfoToModel(model);
        List<ReportListDto> reports = reportListService.findAll();
        model.addAttribute("reports", reports);
        return "ad/reportList";
    }
}
