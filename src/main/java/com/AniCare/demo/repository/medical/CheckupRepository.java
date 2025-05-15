package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.Checkup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckupRepository extends JpaRepository<Checkup, Long> {
}
