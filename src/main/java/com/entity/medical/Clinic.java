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
    @Column(name = "clinic_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne
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
