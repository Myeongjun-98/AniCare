package com.repository.community;

import com.constant.community.MeetingCategory;
import com.entity.community.MeetingBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingBoardRepository extends JpaRepository<MeetingBoard, Long> {

    //모임 모집글에 해당하는 게시글 불러오기
    List<MeetingBoard> findAllByBoardId(Long boardId);

    //모임 모집글-카테고리별로 게시글 불러오기
    List<MeetingBoard> findAllByMeetingCategory(MeetingCategory meetingCategory);
}
