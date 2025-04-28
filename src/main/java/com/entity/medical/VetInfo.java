package com.entity.medical;

import com.constant.medical.OnWork;
import com.constant.medical.PetSpecies;
import com.entity.admin.Hospital;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;

@Getter @Setter @Entity
public class VetInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vetInfoId;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OnWork workStatus;

    private LocalTime onWorkTime;
    private LocalTime offWorkTiem;
}
