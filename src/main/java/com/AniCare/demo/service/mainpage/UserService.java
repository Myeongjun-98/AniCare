package com.AniCare.demo.service.mainpage;


import com.AniCare.demo.DTO.mainpage.UserUpdateDto;
import com.AniCare.demo.Dto.mainpage.PetDetailDto;
import com.AniCare.demo.Dto.mainpage.UserDetailDto;
import com.AniCare.demo.Dto.mainpage.UserInfoDto;
import com.AniCare.demo.constant.MainPage.Authorization;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.medical.VetInfo;
import com.AniCare.demo.repository.MainPage.PetRepository;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.medical.VetRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인된 사용자 정보 반환 (마이페이지 등 공통 사용 가능)
     */
    public UserDetailDto getLoginUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return new UserDetailDto(); // 비로그인 시 기본 빈 객체 반환
        }

        String userEmail = authentication.getName();
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다: " + userEmail));
        return UserDetailDto.from(user);
    }

    /**
     * 이메일 기준 사용자 정보 조회
     */
    public UserDetailDto getUserDetail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다: " + userEmail));
        return UserDetailDto.from(user);
    }

    /**
     * 회원가입 처리 (유저 + 반려동물)
     */
    public void register(@Valid UserInfoDto userInfoDto, PetDetailDto petDetailDto, PasswordEncoder passwordEncoder) {
        User user = User.createUser(userInfoDto, passwordEncoder);
        userRepository.save(user);

        if (petDetailDto.getPetSpecies() != null) {
            Pet pet = Pet.createPet(petDetailDto);
            pet.setUser(user);
            petRepository.save(pet);
        }
    }

    /**
     * Spring Security 인증 처리
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(email).orElse(null);
//                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다: " + email));
        String username =null , password=null, role=null;
        if( user ==null ) {
            VetInfo vetInfo = vetRepository.findByVetId(email);
            username = vetInfo.getVetId();
            password=vetInfo.getVetPassword();
            role= Authorization.USER.toString();
        }else{
            username= user.getUserEmail();
            password=user.getUserPassword();
            role=String.valueOf(user.getAuthorization());
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(password)
                .roles(role)
                .build();
    }

    public boolean isVetLogin(String name) {

        VetInfo vetInfo = vetRepository.findByVetId(name);
        if( vetInfo == null) return false;

        return true;
    }


    // 마이페이지 사용자 정보 수정 모달을 위한 매서드
    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto) throws Exception {

        // 로그인한 사용자 이메일을 통해 사용자 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByUserEmail(userEmail).orElseThrow( () -> new IllegalArgumentException("일치하는 사용자 정보를 찾을 수 없습니다"));

        // 수정 가능한 정보만 업데이트
        if(userUpdateDto.getPassword() != null && userUpdateDto.getPassword().isEmpty()){
            user.setUserPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }
        user.setUserTel(userUpdateDto.getTel());
        user.setUserAddress(userUpdateDto.getAddress());

        MultipartFile img = userUpdateDto.getUserImg();
        if (img != null && !img.isEmpty()) {
            // 이미지 업로드 처리
            String filePath = "/images/" + img.getOriginalFilename();
            img.transferTo(new java.io.File("src/main/resources/static"+filePath));
            user.setUserImage(filePath);
        }
        userRepository.save(user);

    }


}



