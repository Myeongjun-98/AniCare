package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.service.community.BoardService;
import com.AniCare.demo.service.mainpage.MainEnquiryService;
import com.AniCare.demo.service.mainpage.PetService;
import com.AniCare.demo.service.mainpage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private MainEnquiryService mainEnquiryService;
    @Autowired
    private PetService petService;



    @GetMapping("/anicare")
    public String main(Model model){

        // 메인페이지에 커뮤니티 정보글 띄우기
        model.addAttribute("communityList", boardService.getAllboardList());

        // 마이페이지에 사용자 정보 띄우기

        model.addAttribute("userDetailDto", userService.getUserDetail());

        // 마이페이지에 내 반려동물 정보 띄우기
        model.addAttribute("petDetailDto", petService.getPetDetail(3l));


        return "mainpage/mainpage";
    }


    // 메인헤더 커뮤니티 매핑
    @GetMapping("communitymain")
    public String showCommunityMainPage(){
        return "community/board/commain";
    }

}
