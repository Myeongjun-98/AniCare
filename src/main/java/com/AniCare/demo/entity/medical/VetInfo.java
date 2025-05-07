package com.AniCare.demo.entity.medical;

import com.AniCare.demo.constant.medical.OnWork;
import com.AniCare.demo.constant.medical.PetSpecies;
import com.AniCare.demo.entity.admin.Hospital;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;

@Getter @Setter @Entity
public class VetInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vet_info_id")
    private Long id;

    @Column(nullable = false)
    private String vetId;

    @Column(nullable = false)
    private String vetName;

    @Column(nullable = false)
    private String vetPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    private ArrayList<PetSpecies> curingCapable;

    private String profileImage;

    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT '퇴근'")
    @Enumerated(EnumType.STRING)
    private OnWork workStatus;

    private LocalTime onWorkTime;
    private LocalTime offWorkTime;
}
