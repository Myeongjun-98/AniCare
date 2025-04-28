package com.entity.medical;

import com.constant.medical.PetStatus;
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
    @JoinColumn(nullable = false)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Long petId;

    @Column(nullable = false)
    private LocalDateTime clinicDiaryRecordDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Long hospitalId;
}
