package entity.admin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class Hospital {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long hospitalId;
    @Column(nullable = false)
    private String hospitalName;
    @Column(nullable = false)
    private String hospitalTel;
    private String hospitalImage;
    @Column(nullable = false)
    private String hospitalVetId;
    private String device;
    private String operating;

    @Enumerated(EnumType.STRING)
    private ClinicType clinicType;

    @Column(nullable = false)
    private String hospitalAddress;




}
