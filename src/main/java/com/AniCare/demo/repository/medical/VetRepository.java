package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.VetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetRepository extends JpaRepository<VetInfo, Long> {

    // 진단 예약을 위해 수의사 리스트를 별점순으로 불러오기
    @Query("SELECT v FROM VetInfo v JOIN FETCH v.curingCapable")
    List<VetInfo> findAllWithCuringCapable();
}
