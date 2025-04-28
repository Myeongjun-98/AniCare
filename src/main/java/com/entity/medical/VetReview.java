package com.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Entity
public class VetReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vetReviewId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false, unique = true)
    private Clinic clinic;

    private float rating;

    @Column(nullable = false)
    private LocalDateTime reviewDate;


}
