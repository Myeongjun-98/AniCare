// >> (medical) 개발자용 로그인레포지토리
package com.repository.medical;

import com.entity.MainPage.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface devUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
// << (medical) 개발자용 로그인레포지토리