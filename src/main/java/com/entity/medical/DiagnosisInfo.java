package com.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity
public class DiagnosisInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_info_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "diagnosis_disease", joinColumns = @JoinColumn(name = "diagnosis_info_id"))
    @Column(name = "disease")
    private List<Disease> diseases;

    @ElementCollection
    @CollectionTable(name = "diagnosis_allergy", joinColumns = @JoinColumn(name = "diagnosis_info_id"))
    @Column(name = "allergy")
    private List<Allergy> allergies;
}
