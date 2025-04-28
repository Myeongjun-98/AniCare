package com.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Entity
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergyId;

    @Column(nullable = false)
    private String allergyName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Long petId;

}
