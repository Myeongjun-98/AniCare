package com.AniCare.demo.entity.MainPage;

import com.AniCare.demo.constant.MainPage.Authorization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId; // ✅ 추가된 필드

    private String userImage;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userPassword;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String userAddress;

    private String userTel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authorization authorization;
}
