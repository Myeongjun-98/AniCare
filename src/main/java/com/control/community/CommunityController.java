package com.control.community;

import com.Dto.community.BoardDetailDto;
import com.Dto.community.BoardListMainDto;
import com.Dto.community.BoardListSubDto;
import com.service.community.BoardService;
import com.service.community.CommunityMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private CommunityMainService communityMainService;
    @Autowired
    private BoardService boardService;

    @GetMapping("/community/commain")
    public String commainPage(Model model){

        List<BoardListMainDto> boardListMainDtos = communityMainService.getBoardMainList();
        model.addAttribute("boardListMainDtos", boardListMainDtos);

        return "community/commain";
    }

    @GetMapping("/community/comsearch")
    public String comsearch(Model model){

        List<BoardListSubDto> boardListSubDtos = communityMainService.getBoardSearchList();

        model.addAttribute("boardListSubDtos", boardListSubDtos);
        return "community/comsearch";
    }

    @GetMapping("/community/board/boardList")
    public String boardPage(Model model){
        List<BoardListMainDto> boardListDtos = boardService.getBoardList();
        model.addAttribute("boardListDtos", boardListDtos);

        return "community/board/boardList";
    }

    @GetMapping("/community/board/boardDetail/{boardId}")
    public String boardDetailPage(@PathVariable("boardId")Long id, Model model) {

        model.addAttribute("board", boardService.getBoardDetail(id));
        return "community/board/boardDetail";
    }
}
