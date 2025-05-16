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
public class ConsultationChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Consultation_id", nullable = false)
    private Consultation consultation;    // 채팅방 아이디

    @ManyToOne(optional = true)
    @JoinColumn(name = "sender_user_id")
    private User senderUser;    // 일반 유저가 보낸 메시지일 때

    @ManyToOne(optional = true)
    @JoinColumn(name = "sender_vet_id")
    private VetInfo senderVet;  // 수의사가 보낸 메시지일 때

    @Column(nullable = false)
    private LocalDateTime sendAt;   // 메시지 전달 시간

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean readFlag;           // 읽음 여부

}
