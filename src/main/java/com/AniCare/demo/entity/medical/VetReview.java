package com.AniCare.demo.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class VetReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vet_review_id")
    private Long id;    // 수의사 리뷰테이블 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false, unique = true)
    private Clinic clinic;  // 진료기록 정보

    private float rating;   // 별점 (0.5단위)

    @Column(nullable = false)
    private LocalDateTime reviewDate;   // 리뷰 작성 시간


}
