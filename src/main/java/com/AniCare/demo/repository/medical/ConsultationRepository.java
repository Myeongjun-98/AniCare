package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
