package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.PetDetailDto;
import com.AniCare.demo.Dto.mainpage.UserDetailDto;
import com.AniCare.demo.Dto.mainpage.UserInfoDto;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.PetRepository;
import com.AniCare.demo.repository.MainPage.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    // 마이페이지용 사용자 상세 정보 반환
    public UserDetailDto getUserDetail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다: " + userEmail));
        return UserDetailDto.from(user);
    }

    // 🔒 로그인된 사용자 정보 반환 (ModelAttribute로 사용 가능)
    public UserDetailDto getLoginUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return new UserDetailDto(0L, "비회원", ""); // 비로그인 상태 기본 반환
        }

        String userEmail = authentication.getName();
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다: " + userEmail));
        return UserDetailDto.from(user);
    }

    // 회원가입
    public void register(@Valid UserInfoDto userInfoDto, PetDetailDto petDetailDto, PasswordEncoder passwordEncoder) {
        User user = User.createUser(userInfoDto, passwordEncoder);
        userRepository.save(user);

        if (petDetailDto.getPetSpecies() != null) {
            Pet pet = Pet.createPet(petDetailDto);
            pet.setUser(user);
            petRepository.save(pet);
        }
    }

    // 로그인 인증 처리
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserEmail())
                .password(user.getUserPassword())
                .roles(String.valueOf(user.getAuthorization()))
                .build();
    }
}
