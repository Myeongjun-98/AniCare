package com.AniCare.demo.repository.MainPage;

import com.AniCare.demo.entity.medical.VetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository

public interface VetSignupRepository extends JpaRepository<VetInfo, Long> {



}
