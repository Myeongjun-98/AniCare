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
    private int petAge;
    private String petImg;

    public static PetDetailDto from (Pet pet){
        PetDetailDto petDetailDto = new PetDetailDto();

        petDetailDto.setPetName(pet.getPetName());
        petDetailDto.setPetAge(pet.getPetAge());
        petDetailDto.setPetBreed(pet.getPetBreed());
        petDetailDto.setPetSex(pet.getPetSex());
        petDetailDto.setPetSpecies(pet.getPetSpecies());
        petDetailDto.setPetImg(pet.getPetImage());

        return petDetailDto;
    }


}
