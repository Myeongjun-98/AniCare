package com.AniCare.demo.entity.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class MasterAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate createDate;

    public enum Role {
        ADMIN, MANAGER
    }
}
