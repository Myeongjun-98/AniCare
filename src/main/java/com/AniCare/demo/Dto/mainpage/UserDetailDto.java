package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.entity.MainPage.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailDto {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userImg;

    /**
     * User 엔티티 → UserDetailDto 변환
     */
    public static UserDetailDto from(User user) {
        UserDetailDto dto = new UserDetailDto();
        dto.setUserId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setUserEmail(user.getUserEmail());
        dto.setUserAddress(user.getUserAddress());
        dto.setUserImg(user.getUserImage());
        return dto;
    }
}
