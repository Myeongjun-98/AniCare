package com.repository.community;

import com.entity.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> { //Comment 테이블 데이터를 다루는 Repo

    //해당 게시글의 덧글 목록 불러오기
//    List<Comment> findByBoardId(Long boardId);

    //해당 게시글의 덧글 수 가져오기
//    int countAllByBoardId(Long boardId);
}
