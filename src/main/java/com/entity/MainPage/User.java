package com.entity.MainPage;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    private Long userId;

    @Column(nullable = false)
    private String userName; // 유저이릉

    @Column(nullable = false)
    private String userPassword; // 유저 비밀번호

    @Column(nullable = false)
    private String userEmail; // 유저 이메일

    @Column(nullable = false)
    private String userAddress; // 유저 주소

    private String userTel; // 유저 전화번호


}
