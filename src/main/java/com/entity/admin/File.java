package com.entity.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id;

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
