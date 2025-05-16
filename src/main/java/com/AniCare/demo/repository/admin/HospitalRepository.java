package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.admin.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
