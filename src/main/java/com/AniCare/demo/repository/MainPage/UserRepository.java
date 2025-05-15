package com.AniCare.demo.repository.MainPage;

import com.AniCare.demo.entity.MainPage.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인을 위한 쿼리문
    List<User> findByUserEmailAndUserPassword(String userEmail, String userPassword);


    // 회원정보 수정 및 마이페이지에 사용자 정보를 띄우기  위한 쿼리문
    Optional<User> findById(Long userId);


    // 로그인용 메서드 (email 기준 조회)
    Optional<User> findByUserEmail(String email);



}
