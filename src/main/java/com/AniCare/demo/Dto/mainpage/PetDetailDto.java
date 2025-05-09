package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.constant.medical.PetSex;
import com.AniCare.demo.constant.medical.PetSpecies;
import com.AniCare.demo.entity.MainPage.Pet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDetailDto {

    private String petName;
    private PetSex petSex;
    private PetSpecies petSpecies;
    private String petBreed;
    private String petAge;

    public PetDetailDto from (Pet pet){
        PetDetailDto petDetailDto = new PetDetailDto();

        return petDetailDto;
    }
}
