package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.medical.VetInfo;
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
    private boolean isVet = false;

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

    /**
     * User 엔티티 → UserDetailDto 변환
     */
    public static UserDetailDto from(VetInfo vetInfo) {
        UserDetailDto dto = new UserDetailDto();
        dto.setUserId(vetInfo.getId());
        dto.setUserName(vetInfo.getVetName());
        dto.setUserEmail(vetInfo.getVetId());
        dto.setUserImg(vetInfo.getProfileImage());
        dto.setVet(true);
        return dto;
    }

}
