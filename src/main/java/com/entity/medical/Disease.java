package com.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diseaseId;

    @Column(nullable = false)
    private String diseaseName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Long petId;

}
