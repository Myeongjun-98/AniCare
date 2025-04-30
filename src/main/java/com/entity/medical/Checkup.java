package com.entity.medical;

import com.constant.medical.Symptom;
import com.entity.MainPage.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity
public class Checkup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkup_id")
    private Long id;    // 문진표 테이블 고유번호(아이디)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;    // 반려동물 정보

    private String etcSymptom;  // 기타 증상 작성란

    @Enumerated(EnumType.STRING)
    private List<Symptom> symptom;  // 증상 리스트

}
