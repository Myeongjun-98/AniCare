package com.AniCare.demo.entity.MainPage;

import com.AniCare.demo.Dto.mainpage.UserInfoDto;
import com.AniCare.demo.constant.MainPage.Authorization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    public static User createUser(UserInfoDto userInfoDto, PasswordEncoder passwordEncoder) {
        User user = new User();

        user.setUserImage(userInfoDto.getUserImg());
        user.setUserName(userInfoDto.getUserName());
        user.setUserEmail(userInfoDto.getUserEmail());
        user.setUserAddress(userInfoDto.getUserAddress());
        user.setUserTel(userInfoDto.getUserTel());
        user.setAuthorization(Authorization.USER);

        String password = passwordEncoder.encode(userInfoDto.getUserPassword());
        user.setUserPassword(password);

        return user;

    }
    public void updateUser(String userPassword,String userTel, String userAddress, String userImage, PasswordEncoder passwordEncoder) {
        if (userPassword!= null && !userPassword.isEmpty()) {
            this.userPassword = passwordEncoder.encode(userPassword);
        }
        this.userTel = userTel;
        this.userAddress = userAddress;
        this.userImage = userImage;
    }
}
