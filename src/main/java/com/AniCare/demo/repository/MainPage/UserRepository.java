package com.AniCare.demo.repository.MainPage;

import com.AniCare.demo.entity.MainPage.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인할때 필요한 쿼리 동작 메서드
//    User findByUserId(Long id);
}
