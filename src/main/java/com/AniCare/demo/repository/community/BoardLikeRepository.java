package com.AniCare.demo.repository.community;

import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.entity.community.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> { //boardLike 테이블 데이터를 다루는 Repo

}
