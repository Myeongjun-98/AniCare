package entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity
public class DiagnosisInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diagnosisInfoId;

    @ElementCollection
    @CollectionTable(name = "diagnosis_disease", joinColumns = @JoinColumn(name = "diagnosis_info_id"))
    private List<String> diseaseId;

    @ElementCollection
    @CollectionTable(name = "diagnosis_allergy", joinColumns = @JoinColumn(name = "diagnosis_info_id"))
    private List<String> allergyId;
}
