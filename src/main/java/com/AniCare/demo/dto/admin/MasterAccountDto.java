package com.AniCare.demo.dto.admin;

import com.AniCare.demo.constant.MainPage.Authorization;
import com.AniCare.demo.entity.MainPage.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterAccountDto {
    private Long id;
    private String loginId;
    private String name;
    private Authorization authorization;


//    public MasterAccountDto(Long id, String loginId, String name, Authorization authorization) {
//        this.id = id;
//        this.loginId = loginId;
//        this.name = name;
//        this.authorization = authorization;
//
//    }
//
//    public Long getId() { return id; }
//    public String getLoginId() { return loginId; }
//    public String getName() { return name; }
//    public Authorization getAuthorization() { return authorization; }


    // ✅ 정적 팩토리 메서드 추가
    public static MasterAccountDto fromEntity(User account) {
        return new MasterAccountDto(
                account.getId(),
                account.getLoginId(),
                account.getUserName(),
                account.getAuthorization()
        );
    }
}
