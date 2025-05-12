package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.PetDetailDto;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.repository.MainPage.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public PetDetailDto getPetDetail(Long id){
        Pet petDetails = petRepository.findById(id).get();
        List<PetDetailDto> petDetailDtos = new ArrayList<>();

        PetDetailDto petDetailDto = PetDetailDto.from(petDetails);

        return petDetailDto;
    }
}
