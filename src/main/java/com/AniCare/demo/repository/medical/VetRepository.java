package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.VetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<VetInfo, Long> {

    // 진단 예약을 위해 수의사 리스트를 불러오기
    @Query("SELECT DISTINCT v " +
            "FROM VetInfo v " +
            "JOIN FETCH v.hospital " +
            "LEFT JOIN FETCH v.curingCapable")
    List<VetInfo> findAllWithHospitalAndCuringCapable();

    // 수의사 상세정보 불러오기
    @Query("""
              SELECT v 
              FROM VetInfo v
                JOIN FETCH v.hospital
                LEFT JOIN FETCH v.curingCapable
              WHERE v.id = :id
            """)
    Optional<VetInfo> findWithDetailById(@Param("id") Long id);

    VetInfo findByVetId(String email);
    VetInfo findByVetName(String name);
}
