package com.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Getter @Setter @Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;    // 스케쥴테이블 고유번호(아이디)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_info_id", nullable = false)
    private VetInfo vetInfo;    // 수의사 테이블 정보

    private LocalDateTime requestDate;  // 임의로 설정한 일정 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkup_id", nullable = false)
    private Checkup checkup;    // 문진표 정보

    @Column(nullable = false)
    private LocalDateTime scheduleTime; //

    @Column(nullable = false)
    private Boolean isScheduleProcessed;
}
