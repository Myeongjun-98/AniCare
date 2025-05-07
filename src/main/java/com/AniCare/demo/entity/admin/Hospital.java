package com.AniCare.demo.entity.admin;

import com.AniCare.demo.constant.admin.ClinicType;
import com.AniCare.demo.entity.medical.VetInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToOne
    @JoinColumn(name = "vet_info_id")
    private VetInfo vetInfo;

    private String device;
    private String operating;

    @Enumerated(EnumType.STRING)
    private ClinicType clinicType;

    @Column(nullable = false)
    private String hospitalAddress;


}
