package com.AniCare.demo.entity.MainPage;

import com.AniCare.demo.constant.medical.PetSex;
import com.AniCare.demo.constant.medical.PetSpecies;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pet {

    // 반려동물 테이블 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    // 유저 테이블 아이디
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    private String petImage; // 반려동물 프로필 사진
    private String petName;  // 반려동물 이름
    private String petBreed; // 반려동물 품종 
    private int petAge; //반려동물 나이

    @Enumerated(EnumType.STRING)
    private PetSex petSex; // 반려동물 성별

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetSpecies petSpecies; // 반려동물 종류 (묘종 or 견종)


    public void getPetAge(Integer petAge) {
    }

    public void add(Pet pet) {
    }
}
