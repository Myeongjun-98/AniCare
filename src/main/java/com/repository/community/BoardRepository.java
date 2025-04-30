package com.repository.community;

import com.entity.community.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> { //board 테이블 데이터를 다루는 Repo

    //boardId와 관련된 게시글 조회 메서드
    Board findByBoardId(Long Id);

    //게시글 최신순으로 모두 불러오기
    List<Board> findAllByOrderByBoardIdDesc(Pageable pageable);

    //검색 기능
    List<Board> findContainingKeyword(String keyword);

    //게시글 삭제 기능
    void deleteByBoardId(Long boardId);


}
