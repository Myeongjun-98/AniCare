package com.AniCare.demo.repository.MainPage;

import com.AniCare.demo.Dto.mainpage.VetSignupDto;
import com.AniCare.demo.entity.medical.VetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetLoginRepository extends JpaRepository<VetInfo, Long> {
    
    // 수의사 로그인용 메서드
    Optional<VetInfo> findByVetId(String id);



}
