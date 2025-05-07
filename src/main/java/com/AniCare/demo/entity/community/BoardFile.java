package com.AniCare.demo.entity.community;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BoardFile { //게시글에 첨부되는 첨부파일을 저장하는 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id; //파일 아이디

    private String fileOriginalname; //파일 원본 이름
    private String fileSavedname; //파일 저장 이름
    private String fileUrl; //파일 경로
    private String thumbnailYn; //썸네일 여부

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board; //게시글 아이디 Join

}
