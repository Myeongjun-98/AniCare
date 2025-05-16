package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.MainPage.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterAccountRepository extends JpaRepository<User, Long> {
}
