package com.AniCare.demo.control.community;

import com.AniCare.demo.Dto.community.BoardListMainDto;
import com.AniCare.demo.Dto.community.BoardListSubDto;
import com.AniCare.demo.Dto.community.*;
import com.AniCare.demo.Dto.mainpage.UserDetailDto;
import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.service.community.BoardService;
import com.AniCare.demo.service.community.CommentService;
import com.AniCare.demo.service.community.CommunityMainService;
import com.AniCare.demo.service.mainpage.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommunityController {

    @Autowired
    private CommunityMainService communityMainService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper; //JSON 변환용

    public CommunityController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userDetailDto")
    public UserDetailDto addUser(Principal principal) {
        if(principal != null) {
            return userService.getUserDetail(principal.getName());
        }
        return null;
    }


    // ================ 커뮤니티 메인 페이지 ================
    @GetMapping("/community/commain")
    public String commainPage(@Valid BoardSearchForm boardSearchForm,
                              @RequestParam(name = "type", defaultValue = "MEETING") String type,
                              @RequestParam(name = "order", defaultValue = "I") String order,
                              @RequestParam(name = "category", defaultValue = "ALL") String category,
                              Principal principal,
                              Model model){

        List<BoardListMainDto> boardListMainDtos = communityMainService.getBoardMainList();

        //모임 모집글 인기글 5개 가공
        List<BoardListMainDto> popMeeting = boardListMainDtos.stream()
                        .filter(board -> board.getBoardType() == BoardType.MEETING)
                                .limit(5)
                                        .toList();

        //심부름 구인글 인기글 5개 가공
        List<BoardListMainDto> popErrand = boardListMainDtos.stream()
                .filter(board -> board.getBoardType() == BoardType.ERRAND)
                .limit(5)
                .toList();


        model.addAttribute("type", type);
        model.addAttribute("order", order);
        model.addAttribute("category", category);
        model.addAttribute("boardSearchForm", boardSearchForm);
        model.addAttribute("popMeeting", popMeeting);
        model.addAttribute("popErrand",popErrand);

        return "community/commain";
    }

    // ================ 커뮤니티 검색결과 페이지 ================
    @GetMapping("community/comsearch")
    public String comsearch(@Valid BoardSearchForm boardSearchForm,
                            Model model) throws JsonProcessingException {

        List<BoardListMainDto> boardListMainDtos = communityMainService.getBoardSearchList(boardSearchForm);
        String boardsJson = objectMapper.writeValueAsString(boardListMainDtos);

        model.addAttribute("boardSearchForm", boardSearchForm);
        model.addAttribute("boardsJson", boardsJson);
        return "community/comsearch";
    }

    // ================ 커뮤니티 게시판 페이지 ================
    @GetMapping("/community/board/boardList")
    public String boardPage(   @RequestParam("type") String type,
                               @RequestParam("order") String order,
                               @RequestParam("category") String category,
                            Model model){

        List<BoardListMainDto> boardListDtos = boardService.getBoardList(type, order, category);

        model.addAttribute("type", type);
        model.addAttribute("order", order);
        model.addAttribute("category", category);
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

    // -------- 커뮤니티: 좋아요 요청 --------


    // -------- 커뮤니티: 좋아요 취소 요청 --------

    // ================ 커뮤니티 덧글 저장 요청 ================
    @PostMapping("/community/board/boardDetail/commentSave")
    public String commentSave(@Valid CommentForm commentForm,
                              BindingResult bindingResult,
                              Model model){

        //dto에 지정해준 오류가 발생할 시:
        if(bindingResult.hasErrors()){
            BoardDetailDto board = boardService.getBoardDetail(commentForm.getBoardId());
            model.addAttribute("board", board);
            return "community/board/boardDetail";
        }

        try {
            commentService.saveComment(commentForm);
        }catch(Exception e){ //덧글 저장 과정에서 오류 발생시:
            BoardDetailDto board = boardService.getBoardDetail(commentForm.getBoardId());
            model.addAttribute("board", board);
            model.addAttribute("commentError", "덧글 작성 실패");
            return "community/board/boardDetail";

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
                            @RequestParam("boardFile") List<MultipartFile> multipartFileList,
                            Principal principal,
                            Model model){

        if(bindingResult.hasErrors()){
            return "community/board/boardWrite";
        }

        String email = principal.getName();

        try {
            boardService.boardSave(boardForm, multipartFileList, email);
        } catch(Exception e) {
            model.addAttribute("boardError", "게시글 작성 실패");
            return "community/board/boardWrite";
        }

        return "redirect:/community/board/boardList?type="
                + boardForm.getBoardType().name()
                + "&order=I&category="
                + boardForm.getCategory();
    }



}

