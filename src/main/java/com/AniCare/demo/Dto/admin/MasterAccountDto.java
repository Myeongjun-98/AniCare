package com.AniCare.demo.Dto.admin;

import com.AniCare.demo.constant.MainPage.Authorization;
import com.AniCare.demo.entity.MainPage.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterAccountDto {
    private Long id;

    private String name;
    private Authorization authorization;





    // ✅ 정적 팩토리 메서드 추가
    public static MasterAccountDto fromEntity(User account) {
        return new MasterAccountDto(
                account.getUserId(),

                account.getUserName(),
                account.getAuthorization()
        );
    }
}
