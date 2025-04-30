package com.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity @Setter @Getter
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Long id;    // 처방전 테이블 고유번호(아이디)

    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;      // 진료 정보

    private List<String> medicine;  // 약 리스트
    private List<Integer> amount;   // 약 수량 리스트

}
