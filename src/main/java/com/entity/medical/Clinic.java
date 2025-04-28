package com.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clinicId;

    @OneToMany
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @OneToMany
    @JoinColumn(name = "diagnosis_info_id", nullable = false)
    private DiagnosisInfo diagnosisInfo;

    @Column(nullable = false)
    private String opinion;

    @Column(nullable = false)
    private LocalDateTime clinicDate;

    @Column(nullable = false)
    private Boolean deliveryRequest;

    @Column(nullable = false)
    private Boolean ratingRequest;
}
