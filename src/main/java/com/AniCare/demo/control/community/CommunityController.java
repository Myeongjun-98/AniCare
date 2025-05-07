package com.AniCare.demo.control.community;

import com.AniCare.demo.Dto.community.BoardListMainDto;
import com.AniCare.demo.Dto.community.BoardListSubDto;
import com.AniCare.demo.Dto.community.*;
import com.AniCare.demo.service.community.BoardService;
import com.AniCare.demo.service.community.CommentService;
import com.AniCare.demo.service.community.CommunityMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private CommunityMainService communityMainService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;

    // ================ 커뮤니티 메인 페이지 ================
    @GetMapping("/community/commain")
    public String commainPage(Model model){

        List<BoardListMainDto> boardListMainDtos = communityMainService.getBoardMainList();
        model.addAttribute("boardListMainDtos", boardListMainDtos);

        return "community/commain";
    }

    // ================ 커뮤니티 검색결과 페이지 ================
    @GetMapping("/community/comsearch")
    public String comsearch(Model model){

        List<BoardListSubDto> boardListSubDtos = communityMainService.getBoardSearchList();

        model.addAttribute("boardListSubDtos", boardListSubDtos);
        return "community/comsearch";
    }




}
