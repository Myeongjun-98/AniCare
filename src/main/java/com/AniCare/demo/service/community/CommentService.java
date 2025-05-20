package com.AniCare.demo.service.community;

import com.AniCare.demo.Dto.community.CommentForm;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.entity.community.Comment;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.community.BoardRepository;
import com.AniCare.demo.repository.community.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    // ================ 덧글 저장 ================
    public void saveComment(CommentForm commentForm, String email) {


        //회원정보 User에 담아주기
        User user = userRepository.findByUserEmail(email).orElse(null);

        Board board = boardRepository.findById(commentForm.getBoardId()).get();

        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setUser(user);
        comment.setCommentContent(commentForm.getCommentContent());
        comment.setCommentWriteDate(LocalDate.now());

        commentRepository.save(comment);
    }
}
