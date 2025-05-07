package com.AniCare.demo.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class DiagnosisInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_info_id")
    private Long id;        // 진단정보 테이블 고유번호(아이디)

    @ElementCollection
    @CollectionTable(name = "diagnosis_disease", joinColumns = @JoinColumn(name = "diagnosis_info_id"))
    @Column(name = "disease")
    private List<Disease> diseases;     // 질병 리스트

    @ElementCollection
    @CollectionTable(name = "diagnosis_allergy", joinColumns = @JoinColumn(name = "diagnosis_info_id"))
    @Column(name = "allergy")
    private List<Allergy> allergies;    // 알러지 리스트
}
