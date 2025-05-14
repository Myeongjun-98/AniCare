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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private  final PetRepository petRepository;


//    // 로그인
//    public List<UserInfoDto>getUserEmailAndUserPassword(){
//        List<User> users = userRepository.findByUserEmailAndUserPassword();
//        List<UserInfoDto> userInfoDtos = new ArrayList<>();
//
//        return userInfoDtos;
//
//    }

    // 마이페이지에 사용자 정보 띄우기
    public UserDetailDto getUserDetail(String userEmail){
            User user = userRepository.findByUserEmail(userEmail).get();


//        User userDetails = userRepository.findByUserId();
//        List<UserDetailDto> userDetailDtos = new ArrayList<>();
//
//        UserDetailDto userDetailDto = UserDetailDto.from(userDetails);

        return UserDetailDto.from(user);
    }

    private void orElseThrow(Object o) {
    }

    public void register(@Valid UserInfoDto userInfoDto, PetDetailDto petDetailDto, PasswordEncoder passwordEncoder) {

        User user = User.createUser(userInfoDto,passwordEncoder);
        if (petDetailDto.getPetSpecies() != null){
            Pet pet = Pet.createPet(petDetailDto);
            petRepository.save(pet);
        }
        userRepository.save(user);

    }

    // 로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다"+ email));


        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserEmail())
                .password(user.getUserPassword())
                .roles(String.valueOf(user.getAuthorization())).build();

    }

//    // 회원정보수정
//    public List<UserInfoDto>getUserId(){
//        List<User> userList = userRepository.findByUserId(user.getUserId());
//        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
//
//        return userInfoDtoList;
//    }

}
/*
 // 사용자 저장

        if(userImage != null && !userImage.isEmpty()){
            String userImagePath = saveFile(userImage);
            user.setUserImage(userImagePath);
        }

        // 반려동물 정보 저장( 입력된 경우)
        if (petName != null && petName.isBlank()){
            Pet pet = new Pet();
            pet.setPetImage(pet.getPetImage());
            pet.setPetName(petName);
            pet.setPetAge(petAge);
            pet.setPetSex(petSex);
            pet.setPetBreed(petBreed);
            pet.setPetSpecies(petSpecies);

            if(petImage != null && !petImage.isEmpty()){
                String petImagePath = saveFile(petImage);
                pet.setPetImage(petImagePath);
            }

            Pet defaulPet = user.getDefaultPet();
            if (defaulPet != null){
               defaulPet=pet;
            }


            pet.setUser(user);
            user.setDefaultPet().add(pet);

        }
        userRepository.save(user);
 */