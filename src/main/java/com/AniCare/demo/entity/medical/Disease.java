package com.AniCare.demo.entity.medical;

import com.AniCare.demo.entity.MainPage.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Long id;    // 질병테이블 고유번호(아이디)

    @Column(nullable = false)
    private String diseaseName; // 병명

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;    // 반려동물 정보

}
