package com.entity.community;

import com.constant.community.MeetingCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MeetingBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meeting_id")
    private Long Id; //모임 모집글 id

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board; //게시글 id

    @Enumerated(EnumType.STRING)
    private MeetingCategory meetingCategory; //모임 모집글 카테고리
}
