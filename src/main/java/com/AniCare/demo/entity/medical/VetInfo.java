package com.AniCare.demo.entity.medical;

import com.AniCare.demo.constant.medical.OnWork;
import com.AniCare.demo.constant.medical.PetSpecies;
import com.AniCare.demo.entity.admin.Hospital;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
public class VetInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vet_info_id")
    private Long id;    // 수의사 정보테이블 번호(고유번호)

    @Column(nullable = false)
    private String vetId;   // 수의사 아이디(진짜 아이디 Myeongjun-98같은거)

    @Column(nullable = false)
    private String vetName; // 수의사 이름

    @Column(nullable = false)
    private String vetPassword; // 수의사 비밀번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;  // 병원 정보

    @ElementCollection(targetClass = PetSpecies.class)
    @CollectionTable(
            name = "vetinfo_curing_capable",
            joinColumns = @JoinColumn(name = "vet_info_id")
    )
    @Column(name = "pet_species")
    @Enumerated(EnumType.STRING)
    private List<PetSpecies> curingCapable;    // 진료 가능 동물종 리스트

    private String profileImage;    // 수의사 프로필 이미지

    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT '퇴근'")
    @Enumerated(EnumType.STRING)
    private OnWork workStatus;  // 수의사 근태근황

    private LocalTime onWorkTime;   // 수의사 근로시간(시작)
    private LocalTime offWorkTime;  // 수의사 근로시간(끝)
}
