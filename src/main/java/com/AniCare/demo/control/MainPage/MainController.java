package com.AniCare.demo.control.MainPage;


import com.AniCare.demo.service.community.BoardService;
import com.AniCare.demo.service.mainpage.MainEnquiryService;
import com.AniCare.demo.service.mainpage.PetService;
import com.AniCare.demo.service.mainpage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

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

    @GetMapping("/")
    public String mainRedircet(){

        return "redirect:/anicare";
    }



    @GetMapping("/anicare")
    public String main(Principal principal, Model model) {

        // 메인페이지에 커뮤니티 정보글 띄우기
        model.addAttribute("communityList", boardService.getAllboardList());

        if (principal != null) {


            // 마이페이지에 사용자 정보 띄우기
            model.addAttribute("userDetailDto", userService.getUserDetail(principal.getName()));

            // 마이페이지에 내 반려동물 정보 띄우기
            model.addAttribute("petDetailDto", petService.getPetDetail(principal.getName()));
        }

        return "mainpage/mainpage";
    }


    // 메인헤더 커뮤니티 매핑
    @GetMapping("/communitymain")
    public String showCommunityMainPage(){
        return "community/board/commain";
    }


}
