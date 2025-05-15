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


    public static UserDetailDto from(User user){
        UserDetailDto userDetailDto = new UserDetailDto();

        userDetailDto.setUserName(user.getUserName());
        userDetailDto.setUserEmail(user.getUserEmail());
        userDetailDto.setUserAddress(user.getUserAddress());
        userDetailDto.setUserImg(user.getUserImage());
        return userDetailDto;
    }
}
