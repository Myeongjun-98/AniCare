package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.UserDetailDto;
import com.AniCare.demo.Dto.mainpage.UserInfoDto;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



//    // 로그인
//    public List<UserInfoDto>getUserEmailAndUserPassword(){
//        List<User> users = userRepository.findByUserEmailAndUserPassword();
//        List<UserInfoDto> userInfoDtos = new ArrayList<>();
//
//        return userInfoDtos;
//
//    }

    // 마이페이지에 사용자 정보 띄우기
    public UserDetailDto getUserDetail(){
//        User userDetails = userRepository.findByUserId();
//        List<UserDetailDto> userDetailDtos = new ArrayList<>();
//
//        UserDetailDto userDetailDto = UserDetailDto.from(userDetails);

        return null;
    }

    public void register(@Valid UserInfoDto userInfoDto) {
    }

//    // 회원정보수정
//    public List<UserInfoDto>getUserId(){
//        List<User> userList = userRepository.findByUserId(user.getUserId());
//        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
//
//        return userInfoDtoList;
//    }

}
