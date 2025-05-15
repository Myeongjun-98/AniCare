package com.AniCare.demo.entity.medical;

import com.AniCare.demo.entity.MainPage.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id")
    private Long id;    // 알러지테이블 고유번호(아이디)

    @Column(nullable = false)
    private String allergyName; // 알러지 이름

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;    // 반려동물 정보

}
