package com.AniCare.demo.entity.medical;

import com.AniCare.demo.entity.MainPage.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 상담 아이디

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_CONSULT_USER"))
    private User user;   // 일반 유저

    @ManyToOne(optional = false)
    @JoinColumn(name = "vet_id", foreignKey = @ForeignKey(name = "FK_CONSULT_VET"))
    private VetInfo vet;    // 수의사

    @OneToOne(optional = false)
    @JoinColumn(name = "checkup_id", foreignKey = @ForeignKey(name = "FK_CONSULT_CHECKUP"))
    private Checkup checkup;    // 문진표 정보

    private Boolean isEnd = false;  // 끝난 상담인지 여부

    @Column(nullable = false)   // 상담 시작 시간
    private LocalDateTime startedAt;

    @PrePersist
    public void initStartedAt() {
        this.startedAt = LocalDateTime.now();
    }
}
