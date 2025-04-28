package com.repository.medical;

import com.entity.medical.VetReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetReviewRepository extends JpaRepository<VetReview, Long> {
}