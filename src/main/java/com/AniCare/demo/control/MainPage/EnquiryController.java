package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.Dto.mainpage.EnquiryListDto;
import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.admin.EnquiryReply;
import com.AniCare.demo.service.mainpage.MainEnquiryService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EnquiryController {

    private final MainEnquiryService mainEnquiryService;

    @GetMapping("/anicare/enquirylist")
    public String enquiryList(@RequestParam(defaultValue = "1") int page, Model model) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("enquiryDate").descending());
        Page<Enquiry> enquiries = mainEnquiryService.getAllEnquiries(pageable);

        model.addAttribute("enquiries", enquiries);
        model.addAttribute("currentPage", page);

        return "mainpage/enquirylist";
    }
}
