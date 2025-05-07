package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.VetReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetReviewRepository extends JpaRepository<VetReview, Long> {
}
