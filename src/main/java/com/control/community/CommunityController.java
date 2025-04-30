package com.control.community;

import com.Dto.community.BoardListMainDto;
import com.service.community.CommunityMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private CommunityMainService communityMainService;

    @GetMapping("/community/commain")
    public String commainPage(Model model){

        List<BoardListMainDto> boardListMainDtos = communityMainService.getBoardList();
        model.addAttribute("boardListMainDtos", boardListMainDtos);

        return "community/commain";
    }
}
