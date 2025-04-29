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
    private Long blId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private LocalDate blDate;
    private String blReason;

}
