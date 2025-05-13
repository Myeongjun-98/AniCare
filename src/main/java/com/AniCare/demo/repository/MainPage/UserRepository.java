package com.AniCare.demo.repository.MainPage;

import com.AniCare.demo.entity.MainPage.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인을 위한 쿼리문
    List<User> findByUserEmailAndUserPassword(String userEmail, String userPassword);



    // 회원정보 수정 및 마이페이지에 사용자 정보를 띄우기  위한 쿼리문
    User findByUserId(Long userId);

    org.springframework.security.core.userdetails.User findByUserEmail(String email);
}
