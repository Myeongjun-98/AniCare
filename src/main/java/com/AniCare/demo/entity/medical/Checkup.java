package com.AniCare.demo.entity.medical;

import com.AniCare.demo.constant.medical.Symptom;
import com.AniCare.demo.entity.MainPage.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Checkup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkup_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    private String etcSymptom;

    @Enumerated(EnumType.STRING)
    private List<Symptom> symptom;

}
