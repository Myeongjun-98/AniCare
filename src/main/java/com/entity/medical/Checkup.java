package com.entity.medical;

import com.constant.medical.Symptom;
import com.entity.MainPage.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Checkup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    private String etcSymptom;

    @Enumerated(EnumType.STRING)
    private Symptom symptom;

}
