package com.AniCare.demo.repository.medical;

import com.AniCare.demo.DTO.medical.VetAverageReviewDto;
import com.AniCare.demo.entity.medical.VetReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetReviewRepository extends JpaRepository<VetReview, Long> {

    // 수의사별 평균 별점을 계산해 DTO로 반환
    @Query("""
              SELECT new com.AniCare.demo.DTO.medical.VetAverageReviewDto(
                vr.clinic.schedule.vetInfo.id,
                COALESCE(AVG(vr.rating), 0)
              )
              FROM VetReview vr
              GROUP BY vr.clinic.schedule.vetInfo.id
            """)
    List<VetAverageReviewDto> findAverageRatings();
}

