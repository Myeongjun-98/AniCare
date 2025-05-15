package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.PetDetailDto;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.PetRepository;
import com.AniCare.demo.repository.MainPage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetDetailDto getPetDetail(String userEmail) {

        User user = userRepository.findByUserEmail(userEmail).get();

        Pet petDetails = petRepository.findByUserId(user.getId());

        if (petDetails == null) {
            return null;
        }
        List<PetDetailDto> petDetailDtos = new ArrayList<>();

        PetDetailDto petDetailDto = PetDetailDto.from(petDetails);

        return petDetailDto;
    }
}
