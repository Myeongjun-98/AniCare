package com.repository.community;

import com.Dto.community.BoardSumDto;
import com.entity.community.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> { //boardLike 테이블 데이터를 다루는 Repo

    //좋아요 수 가져오기
    int countByBoardId(Long boardId);
}
