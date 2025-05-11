package com.AniCare.demo.service.medical;

import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.medical.devUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// >> (medical) 개발자용 로그인서비스
@Service
@RequiredArgsConstructor
public class devUserService implements UserDetailsService {
    private final devUserRepository devUserRepository;

    // (임시) 로그인상태의 유저이름으로 유저정보 불러오기
    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User u = devUserRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName + " not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getUserName())           // 이제 userName 필드를
                .password(u.getUserPassword())       // 암호화된 password
                .roles(u.getAuthorization().name())  // ADMIN or USER
                .build();
    }
}

// << (medical) 개발자용 로그인서비스