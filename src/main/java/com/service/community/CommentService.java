package com.service.community;

import com.Dto.community.CommentForm;
import com.entity.MainPage.User;
import com.entity.community.Board;
import com.entity.community.Comment;
import com.repository.MainPage.UserRepository;
import com.repository.community.BoardRepository;
import com.repository.community.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void saveComment(CommentForm commentForm){

        commentForm.setUserId(3L); //입력 체크용 임시

        Board board = boardRepository.findById(commentForm.getBoardId()).get();
        User user = userRepository.findById(commentForm.getUserId()).get();

        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setUser(user);
        comment.setCommentContent(commentForm.getCommentContent());
        comment.setCommentWriteDate(LocalDate.now());

        commentRepository.save(comment);
    }
}
