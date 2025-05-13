package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.Dto.mainpage.UserInfoDto;
import com.AniCare.demo.constant.medical.PetSex;
import com.AniCare.demo.constant.medical.PetSpecies;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.service.mainpage.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/usersignup")
    public String usersignForm(Model model){
        model.addAttribute("user", new UserInfoDto());

        return "mainpage/usersignup";
    }

    @PostMapping("/usersignup")
    public String signupSubmit(@ModelAttribute("user") @Valid UserInfoDto userInfoDto, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "mainpage/usersignup";
        }

        try{
            userService.register(userInfoDto);
            redirectAttributes.addAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해주세요");
            return "redirect/mainpage/userlogin";
        } catch (DuplicateKeyException e){
            result.rejectValue("email", "error.user", "이미 사용중인 이메일 입니다.");
            return "mainpage/usersignup";
        }
    }
    @GetMapping("/mainpage/usersignup")
    public  String showSignupForm(Model model){
        model.addAttribute("userInfo", new UserInfoDto());

        return "mainpage/usersignup";

    }
    @PostMapping("/mainpage/usersignup")
    public String UserSave(@ModelAttribute UserInfoDto userInfoDto, @RequestParam(required = false)MultipartFile userImage, @RequestParam(required = false) MultipartFile petImage,
                           @RequestParam(required = false) String petName, @RequestParam(required = false) Integer petAge, @RequestParam(required = false) String petBreed, @RequestParam(required = false) PetSex petSex, @RequestParam (required = false) PetSpecies petSpecies )throws IOException {

        // 사용자 저장
        User user = new User();
        user.setUserImage(userInfoDto.getUserImg());
        user.setUserName(userInfoDto.getUserName());
        user.setUserPassword(userInfoDto.getUserPassword());
        user.setUserEmail(userInfoDto.getUserEmail());
        user.setUserAddress(userInfoDto.getUserAddress());
        user.setUserTel(userInfoDto.getUserTel());
        
        if(userImage != null && !userImage.isEmpty()){
            String userImagePath = saveFile(userImage);
            user.setUserImage(userImagePath);
        }

        // 반려동물 정보 저장( 입력된 경우)
        if (petName != null && petName.isBlank()){
            Pet pet = new Pet();
            pet.setPetImage(pet.getPetImage());
            pet.setPetName(petName);
            pet.getPetAge(petAge);
            pet.setPetSex(petSex);
            pet.setPetBreed(petBreed);
            pet.setPetSpecies(petSpecies);

            if(petImage != null && !petImage.isEmpty()){
                String petImagePath = saveFile(petImage);
                pet.setPetImage(petImagePath);
            }

            Pet defaulPet = user.getDefaultPet();
            if (defaulPet != null){
               defaulPet.add(pet);
            }


            pet.setUser(user);
            user.getDefaultPet().add(pet);

        }
        userRepository.save(user);

        return "redirect:/mainpage/userlogin";
    }

    private String saveFile(MultipartFile userImage) {
        return null;
    }


    @GetMapping("/mainpage/userlogin")
    public String loginForm(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String excetion,Model model){
        model.addAttribute("error", error);
        model.addAttribute("excetion",excetion);
        return "mainpage/userlogin";

    }

    @PostMapping("/mainpage/userlogin")
    public String processRegisration(@ModelAttribute UserInfoDto userInfoDto){
        return "redirect:/anicare";
    }

}
