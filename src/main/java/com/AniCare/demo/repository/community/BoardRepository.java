package com.AniCare.demo.repository.community;

import com.AniCare.demo.Dto.mainpage.SearchResultDto;
import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.community.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> { //board 테이블 데이터를 다루는 Repo


    //게시글 좋아요수 순으로 모두 불러오기
    List<Board> findAllByOrderByLikeCountDesc();

    //게시글 최신순으로 모두 불러오기
    List<Board> findAllByOrderByIdDesc();

    //게시글 최신순으로 모두 불러오기(게시판별로_모임모집글 or 심부름구인글)
    List<Board> findAllByBoardTypeOrderByIdDesc(BoardType type);

    //검색 기능
    @Query("SELECT b " +
            "FROM Board b " +
            "WHERE (b.boardTitle LIKE %:keyword% " +
                    "OR b.boardContent LIKE %:keyword%)" +
                    "ORDER BY b.Id DESC")
    List<Board> searchByBoardTypeAndKeyword(@Param("keyword") String keyword);


    List<Board> findTop5ByOrderByLikeCountDesc();

    // 메인페이지 통합검색창 (검색어 포함 + 대상타입 + 최신순 정렬)
        @Query("SELECT b FROM Board b " +
                "WHERE b.boardType IN :types " +
                "AND (LOWER(b.boardTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                "     OR LOWER(b.boardContent) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
                "ORDER BY b.boardWriteDate DESC")
        List<Board> searchByKeywordAndType(@Param("types") List<BoardType> types, @Param("keyword") String keyword);



}
