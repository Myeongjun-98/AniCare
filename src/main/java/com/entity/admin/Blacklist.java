package com.entity.admin;

import com.entity.MainPage.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Blacklist {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bl_id")
    private Long blId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate blDate;

    @Column(nullable = false)
    private String blReason;

}
