package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.entity.MainPage.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {

    @NotEmpty(message = "이름은 필수 입력 사항입니다.")
    private String userName;
    @NotEmpty(message = "비밀번호는 필수 입력사항입니다.")
    @Size(min = 8, max = 12, message = "8자 이상 12자 미만으로 입력하세요")
    private String userPassword;
    @NotEmpty(message = "이메일은 필수 입력사항입니다.")
    private String userEmail;
    private String userAddress;
    private String userTel;


}
