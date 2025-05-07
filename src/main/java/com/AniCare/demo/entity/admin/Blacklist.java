package com.AniCare.demo.entity.admin;

import com.AniCare.demo.entity.MainPage.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private LocalDate blDate;
    private String blReason;

}
