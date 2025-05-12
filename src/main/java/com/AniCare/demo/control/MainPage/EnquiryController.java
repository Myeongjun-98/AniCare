package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.service.mainpage.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    // 내 문의사항 리스트
    @GetMapping("/enquirylist")
    public String enquirylist(Model model){

        List<Enquiry> enquiries = enquiryService.getAllEnquiryList();
        // 내 문의사항 리스트 띄우기
        model.addAttribute("enquiryList", enquiries);


        return "mainpage/enquirylist";
    }

}
