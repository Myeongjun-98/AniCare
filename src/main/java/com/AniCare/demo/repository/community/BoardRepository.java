package com.AniCare.demo.repository.community;

import com.AniCare.demo.entity.community.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> { //board 테이블 데이터를 다루는 Repo


    //게시글 최신순으로 모두 불러오기
    List<Board> findAllByOrderByIdDesc();

    //검색 기능
    List<Board> findByBoardTitleContaining(String keyword);



}
