package com.control.community;

import com.Dto.community.BoardDetailDto;
import com.Dto.community.BoardListMainDto;
import com.Dto.community.BoardListSubDto;
import com.Dto.community.CommentForm;
import com.entity.community.Comment;
import com.service.community.BoardService;
import com.service.community.CommentService;
import com.service.community.CommunityMainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private CommunityMainService communityMainService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;

    //커뮤니티 메인 페이지
    @GetMapping("/community/commain")
    public String commainPage(Model model){

        List<BoardListMainDto> boardListMainDtos = communityMainService.getBoardMainList();
        model.addAttribute("boardListMainDtos", boardListMainDtos);

        return "community/commain";
    }

    //커뮤니티 검색결과 페이지
    @GetMapping("/community/comsearch")
    public String comsearch(Model model){

        List<BoardListSubDto> boardListSubDtos = communityMainService.getBoardSearchList();

        model.addAttribute("boardListSubDtos", boardListSubDtos);
        return "community/comsearch";
    }

    //커뮤니티 게시판(모임, 심부름) 페이지
    @GetMapping("/community/board/boardList")
    public String boardPage(Model model){
        List<BoardListMainDto> boardListDtos = boardService.getBoardList();
        model.addAttribute("boardListDtos", boardListDtos);

        return "community/board/boardList";
    }

    //게시글 상세보기
    @GetMapping("/community/board/boardDetail/{boardId}")
    public String boardDetailPage(@PathVariable("boardId")Long id, Model model) {

        CommentForm commentForm = new CommentForm();
        commentForm.setBoardId(id);

        model.addAttribute("board", boardService.getBoardDetail(id));
        model.addAttribute("commentForm", commentForm);

        return "community/board/boardDetail";
    }

    //게시글 덧글 저장 요청
    @GetMapping("/community/board/boardDetail/commentSave")
    public String commentSave(@Valid CommentForm commentForm,
                                BindingResult bindingResult,
                                Model model){

        if(bindingResult.hasErrors()){
            return "community/board/boardList";
        }

        try {
            commentService.saveComment(commentForm);
        }catch(Exception e){
            model.addAttribute("commentError", "덧글 작성 실패");
            return "community/board/boardList";

        }

        return "redirect:/community/board/boardDetail/"+commentForm.getBoardId();
    }
}
