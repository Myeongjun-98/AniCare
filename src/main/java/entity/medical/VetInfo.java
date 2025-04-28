package entity.medical;

import constant.medical.OnWork;
import constant.medical.PetSpecies;
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
    private Long hospitalId;

    private ArrayList<PetSpecies> curingCapable;

    private String profileImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OnWork workStatus;

    private LocalTime onWorkTime;
    private LocalTime offWorkTiem;
}
