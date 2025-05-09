package com.AniCare.demo.control.admin;

import com.AniCare.demo.dto.admin.*;
import com.AniCare.demo.service.adminService.EnquiryService;
import com.AniCare.demo.service.adminService.HospitalService;
import com.AniCare.demo.service.adminService.MasterAccountService;
import com.AniCare.demo.service.adminService.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<HospitalDto> hospitalList = hospitalService.findAll();

        model.addAttribute("hospitals", hospitalList);
        model.addAttribute("hospital", new HospitalDto());
        return "ad/hospital";
    }
    @GetMapping("/ad/hospitalList")
    public String hospitalList(Model model) {
        List<HospitalDto> hospitalList = hospitalService.findAll();
        model.addAttribute("hospitals", hospitalList);
        return "ad/hospitalList"; // 이 이름의 HTML이 필요함
    }
    @PostMapping("/ad/hospital")
    public String registerHospital(@ModelAttribute HospitalDto hospitalDto) {
        hospitalService.save(hospitalDto);
        return "redirect:/ad/hospital";
    }



    @GetMapping("/ad/enquiryReply")
    public String enquiryReplyPage(Model model) {
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
    @PostMapping("/notice/update")
    public String updateNotice(@ModelAttribute NoticeDto noticeDto) {
        noticeService.update(noticeDto.getId(), noticeDto); // id는 반드시 DTO에 있어야 함
        return "redirect:/ad/notice";
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
