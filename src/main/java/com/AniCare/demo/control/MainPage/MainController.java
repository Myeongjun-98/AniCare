package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.service.community.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    private BoardService boardService;

    @GetMapping("/anicare")
    public String main(Model model){

        model.addAttribute("communityList", boardService.getAllboardList());


        return "mainpage/mainpage";
    }
}
