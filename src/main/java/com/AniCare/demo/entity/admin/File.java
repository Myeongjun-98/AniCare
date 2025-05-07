package com.AniCare.demo.entity.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    @ManyToOne
    @JoinColumn(name="notice_id")
    private Notice notice;
    @Column(nullable = false)
    private String fileOriginalName;
    @Column(nullable = false)
    private String fileSaveName;
    @Column(nullable = false)
    private String fileUrl;


}
