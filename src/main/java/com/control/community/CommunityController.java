package com.control.community;

import com.Dto.community.*;
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
import org.springframework.web.bind.annotation.PostMapping;

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

    // ================ 커뮤니티 게시판 페이지 ================
    @GetMapping("/community/board/boardList")
    public String boardPage(Model model){
        List<BoardListMainDto> boardListDtos = boardService.getBoardList();
        model.addAttribute("boardListDtos", boardListDtos);

        return "/community/board/boardList";
    }

    // ================ 커뮤니티 게시글 상세보기 페이지 ================
    @GetMapping("/community/board/boardDetail/{boardId}")
    public String boardDetailPage(@PathVariable("boardId")Long id, Model model) {

        CommentForm commentForm = new CommentForm();
        commentForm.setBoardId(id);

        model.addAttribute("board", boardService.getBoardDetail(id));
        model.addAttribute("commentForm", commentForm);

        return "community/board/boardDetail";
    }

    // ================ 커뮤니티 덧글 저장 요청 ================
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

    // ================ 커뮤니티 게시글 작성 페이지 ================
    @GetMapping("/community/board/boardWrite")
    public String boardWritePage(Model model){
        BoardForm boardForm = new BoardForm();

        model.addAttribute("boardForm", boardForm);
        return "/community/board/boardWrite";
    }

    // ================ 커뮤니티 게시글 작성 요청 ================
    @PostMapping("/community/board/boardWrite/boardSave")
    public String boardSave(@Valid BoardForm boardForm,
                            BindingResult bindingResult,
                            Model model, String category){

        try {
            boardService.boardSave(boardForm, category);
        } catch(Exception e) {
            return "/community/board/boardWrite";
        }

        return "/community/board/boardList";
    }



}
