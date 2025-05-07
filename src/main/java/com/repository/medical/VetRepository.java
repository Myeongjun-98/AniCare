package com.repository.medical;

import com.entity.medical.VetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetRepository extends JpaRepository<VetInfo, Long> {

    // 진단 예약을 위해 수의사 리스트 불러오기
    List<VetInfo> findAll();
}
