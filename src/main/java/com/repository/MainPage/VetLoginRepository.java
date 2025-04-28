package com.repository.MainPage;

import com.entity.medical.VetInfo;
import com.sun.tools.javac.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetLoginRepository extends JpaRepository<VetInfo, Long> {

    // 수의사 로그인을 위한 쿼리 동작 메서드
    VetInfo findByVetId(Long id);

}
