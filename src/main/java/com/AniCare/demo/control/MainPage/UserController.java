package com.AniCare.demo.control.MainPage;

import com.AniCare.demo.Dto.mainpage.PetDetailDto;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/usersignup")
    public String usersignForm(Model model){
        model.addAttribute("user", new UserInfoDto());


        return "mainpage/usersignup";
    }

//    @PostMapping("/usersignup")
//    public String signupSubmit(@ModelAttribute("user") @Valid UserInfoDto userInfoDto, BindingResult result, RedirectAttributes redirectAttributes){
//        if(result.hasErrors()){
//            return "mainpage/usersignup";
//        }
//
//        try{
//            userService.register(userInfoDto,passwordEncoder);
//            redirectAttributes.addAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해주세요");
//            return "redirect/mainpage/userlogin";
//        } catch (DuplicateKeyException e){
//            result.rejectValue("email", "error.user", "이미 사용중인 이메일 입니다.");
//            return "mainpage/usersignup";
//        }
//    }
    @GetMapping("/mainpage/usersignup")
    public  String showSignupForm(Model model){
        model.addAttribute("userInfo", new UserInfoDto());
        model.addAttribute("petDetailDto", new PetDetailDto());
        return "mainpage/usersignup";

    }
    @PostMapping("/mainpage/usersignup")
    public String UserSave(@ModelAttribute("user") UserInfoDto userInfoDto, @RequestParam(required = false)MultipartFile userImage, @RequestParam(required = false) MultipartFile petImage,
                           @ModelAttribute("petDetailDto") PetDetailDto petDetailDto )throws IOException {

        userService.register(userInfoDto,petDetailDto,passwordEncoder);

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

    @GetMapping("/ad")
    public String adminMain(Principal principal, Model model){

    return null;
    }



}
