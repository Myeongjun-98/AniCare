package com.AniCare.demo.entity.community;

import com.AniCare.demo.constant.community.ErrandCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ErrandBoard { //심부름 구인글 게시판을 분류 및 저장하는 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "errand_id")
    private Long id; //심부름 구인글 게시판 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; //게시글 id

    @Enumerated(EnumType.STRING)
    private ErrandCategory errandCategory; //심부름 구인글 카테고리

}
