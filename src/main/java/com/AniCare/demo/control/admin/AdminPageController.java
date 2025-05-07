package com.AniCare.demo.control.admin;

import com.AniCare.demo.DTO.admin.*;
import com.AniCare.demo.service.adminService.EnquiryService;
import com.AniCare.demo.service.adminService.HospitalService;
import com.AniCare.demo.service.adminService.MasterAccountService;
import com.AniCare.demo.service.adminService.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminPageController {

    private final EnquiryService enquiryService;
    private final HospitalService hospitalService;
    private final MasterAccountService masterAccountService;
    private final NoticeService noticeService;

    @GetMapping("/ad/hospital")
    public String hospitalPage(Model model) {
        List<HospitalDto> hospitals = hospitalService.findAll();
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("hospital", new HospitalDto());
        return "ad/hospital";
    }

    @GetMapping("/ad/enquiryReply")
    public String enquiryReplyPage(Model model) {
        List<EnquiryReplyViewDto> enquiries = enquiryService.findAll();
        model.addAttribute("enquiries", enquiries);
        return "ad/enquiryReply";
    }


    @PostMapping("ad/enquiryReply")
    public String replyToEnquiry(@RequestParam("id") Long enquiryId,
                                 @RequestParam("reply") String reply) {
        enquiryService.reply(enquiryId, reply);
        return "redirect:/ad/enquiryReply";
    }

    @GetMapping("/ad/masterAd")
    public String masterAdPage(Model model) {
        List<MasterAccountDto> accounts = masterAccountService.findAll();
        model.addAttribute("userList", accounts);
        return "ad/masterAd";
    }

    @GetMapping("/ad/notice")
    public String noticePage(Model model) {
        List<NoticeListDto> notices = noticeService.getNoticeList();
        model.addAttribute("notices", notices);
        return "ad/notice";
    }
    @PostMapping("/notice/new")
    public String saveNotice(@ModelAttribute NoticeDto noticeDto){
        noticeService.save(noticeDto);
        return  "redirect:/ad/notice";
    }

    @GetMapping("/ad")
    public String adHomePage() {
        return "ad/ad";
    }

    @GetMapping("/ad/form")
    public String formPage() {
        return "ad/form";
    }
}
