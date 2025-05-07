package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.VetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<VetInfo, Long> {
}
