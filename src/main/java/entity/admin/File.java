package entity.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class File {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long filedId;
    private Long noticeId;
    @Column(nullable = false)
    private Long fileOriginalName;
    @Column(nullable = false)
    private Long fileSaveName;
    @Column(nullable = false)
    private String fileUrl;


}
