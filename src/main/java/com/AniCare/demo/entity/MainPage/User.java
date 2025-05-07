package com.AniCare.demo.entity.MainPage;

import com.AniCare.demo.constant.MainPage.Authorization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    // 유저테이블 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userImage; // 유저 프로필 사진

    @Column(nullable = false)
    private String userName; // 유저이릉

    @Column(nullable = false)
    private String userPassword; // 유저 비밀번호

    @Column(nullable = false)
    private String userEmail; // 유저 이메일

    @Column(nullable = false)
    private String userAddress; // 유저 주소

    private String userTel; // 유저 전화번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authorization authorization; // 권한 (사용자 or 관리자)


}
