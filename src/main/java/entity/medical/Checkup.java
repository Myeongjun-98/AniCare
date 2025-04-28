package entity.medical;

import constant.medical.Symptom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Checkup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Long petId;

    private String etcSymptom;

    @Enumerated(EnumType.STRING)
    private Symptom symptom;

}
