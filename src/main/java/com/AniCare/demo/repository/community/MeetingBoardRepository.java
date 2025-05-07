package com.AniCare.demo.repository.community;

import com.AniCare.demo.constant.community.MeetingCategory;
import com.AniCare.demo.entity.community.MeetingBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingBoardRepository extends JpaRepository<MeetingBoard, Long> {


    //모임 모집글-카테고리별로 게시글 불러오기
    List<MeetingBoard> findAllByMeetingCategory(MeetingCategory meetingCategory);

    //심부름 구인글 게시판에 해당되는 게시글(1개) 불러오기
    MeetingBoard findByBoardId(Long boardId);
}
