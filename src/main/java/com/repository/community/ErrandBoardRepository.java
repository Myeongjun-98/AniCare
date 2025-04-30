package com.repository.community;

import com.constant.community.ErrandCategory;
import com.entity.community.ErrandBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrandBoardRepository extends JpaRepository<ErrandBoard, Long> { //errand_board 테이블 데이터를 다루는 Repo

    //심부름 구인글 게시판에 해당되는 게시글 불러오기
//    List<ErrandBoard> findAllByBoardId(Long boardId);

    //심부름 구인글 - 카테고리별로 게시글 불러오기
//    List<ErrandBoard> findAllByErrandCategory(ErrandCategory errandCategory);
}
