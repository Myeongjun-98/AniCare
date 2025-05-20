package com.AniCare.demo.Dto.mainpage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserUpdateDto {

    private String password;
    private String tel;
    private String address;
    private MultipartFile userImg;
}
