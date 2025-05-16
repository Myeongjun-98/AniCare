package com.AniCare.demo.entity.admin;

import com.AniCare.demo.constant.admin.ClinicType;
import com.AniCare.demo.entity.medical.VetInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    @Column(nullable = false)
    private String hospitalName;

    @Column(nullable = false)
    private String hospitalTel;

    private String hospitalImage;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<VetInfo> vetInfos = new ArrayList<>();

    private String device;
    private String operating;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "hospital_clinic_type", joinColumns = @JoinColumn(name = "hospital_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "clinic_type")
    private List<ClinicType> clinicTypes = new ArrayList<>();

    @Column(nullable = false)
    private String hospitalAddress;


}
