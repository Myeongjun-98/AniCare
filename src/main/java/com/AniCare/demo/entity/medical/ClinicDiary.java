package com.AniCare.demo.entity.medical;

import com.AniCare.demo.constant.medical.PetStatus;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.admin.Hospital;
import com.AniCare.demo.entity.community.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Entity
public class ClinicDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_diary_id")
    private Long id;        // 진료수첩 고유번호(아이디)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;  // 보드(게시판)테이블 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;        // 반려동물 정보

    @Column(nullable = false)
    private LocalDateTime clinicDiaryRecordDate;    // 진료수첩 설정한 날짜

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;       // 반려동물 병 상태 ( 판정, 투병중, 완치, 기타 )

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "hospital_id", nullable = true)
    private Hospital hospital;      // 병원테이블 정보
}
