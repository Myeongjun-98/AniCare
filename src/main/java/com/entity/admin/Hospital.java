package com.entity.admin;
import com.constant.admin.ClinicType;
import com.entity.medical.VetInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class Hospital {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
