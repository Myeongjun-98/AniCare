package entity.MainPage;

import jakarta.persistence.*;

public class Pet {

    // 반려동물 테이블 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long petId;

    // 유저 테이블 아이디
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    
    private String petName;  // 반려동물 이름
    private String petBreed; // 반려동물 품종 
    private int petAge; //반려동물 나이
    

}
