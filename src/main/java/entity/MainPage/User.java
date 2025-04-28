package entity.MainPage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    // 유저테이블 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    private String userName; // 유저이릉
    private String userPassword; // 유저 비밀번호
    private String userEmail; // 유저 이메일
    private String userAddress; // 유저 주소
    private String userTel; // 유저 전화번호

}
