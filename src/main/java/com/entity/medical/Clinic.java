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
    private Long id;    // 진료테이블 고유번호(아이디)

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;  // 스케쥴 테이블 정보

    @ManyToOne
    @JoinColumn(name = "diagnosis_info_id", nullable = false)
    private DiagnosisInfo diagnosisInfo;    // 진단정보 테이블 정보

    @Column(nullable = false)
    private String opinion;             // 소견 작성란

    @Column(nullable = false)
    private LocalDateTime clinicDate;   // 진료진행한 시간

    @Column(nullable = false)
    private Boolean deliveryRequest;    // 배달신청여부

    @Column(nullable = false)
    private Boolean ratingRequest;      // 리뷰유무
}
