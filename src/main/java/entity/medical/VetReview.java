package entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Entity
public class VetReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vetReviewId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = true)
    private Long clinicId;

    private float rating;

    @Column(nullable = false)
    private LocalDateTime reviewDate;


}
