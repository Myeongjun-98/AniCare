package com.entity.medical;

import com.constant.medical.PetStatus;
import com.entity.MainPage.Pet;
import com.entity.admin.Hospital;
import com.entity.community.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Entity
public class ClinicDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clinicDiaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(nullable = false)
    private LocalDateTime clinicDiaryRecordDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
