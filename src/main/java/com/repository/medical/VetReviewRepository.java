package com.repository.medical;

import com.entity.medical.VetReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetReviewRepository extends JpaRepository<VetReview, Long> {

    // 모든 별점 정보 가져오기
    List<VetReview> findAll();
}

