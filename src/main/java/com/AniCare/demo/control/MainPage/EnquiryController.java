package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.Dto.mainpage.EnquiryDetailDto;
import com.AniCare.demo.Dto.mainpage.EnquiryListDto;
import com.AniCare.demo.Dto.mainpage.UserDetailDto;
import com.AniCare.demo.Dto.mainpage.UserInfoDto;
import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.admin.EnquiryReply;
import com.AniCare.demo.service.mainpage.MainEnquiryService;
import com.AniCare.demo.service.mainpage.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mainpage")
public class EnquiryController {


    private final MainEnquiryService mainEnquiryService;
    private final UserService userService;

    // 내 문의사항 리스트
    @GetMapping("/enquirylist")
    public String enquiryList(@RequestParam(defaultValue = "1") int page, Principal principal, Model model) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("enquiryDate").descending());
        Page<Enquiry> enquiries = mainEnquiryService.getAllEnquiries(pageable);


        model.addAttribute("enquiries", enquiries);
        model.addAttribute("currentPage", page);
        model.addAttribute("userDetailDto",userService.getUserDetail(principal.getName()) );
        return "mainpage/enquirylist";
    }

    // 문의사항 작성
    @GetMapping("/enquirywrite")
    public String enquiryWrite(Principal principal, Model model){
       Enquiry enquiry = new Enquiry();
        String useremail = principal.getName();

        model.addAttribute("enquiries", enquiry);
        model.addAttribute("userDetailDto",userService.getUserDetail(principal.getName()) );
        return "mainpage/enquirywrite";
    }

//    @PostMapping("/enquirysave")
//    public String enquiryWriteSave(@Valid())


    // 문의사항 상세보기
    @GetMapping("/enquirydetail/{enquiryId}")
    public String enquiryDetail(@PathVariable("enquiryId") Long id, Principal principal, Model model){

        EnquiryDetailDto enquiryDetailDto = new EnquiryDetailDto();


        model.addAttribute("enquiries",enquiryDetailDto);
        model.addAttribute("userDetailDto",userService.getUserDetail(principal.getName()) );
        return "mainpage/enquirydetail";
    }


}
