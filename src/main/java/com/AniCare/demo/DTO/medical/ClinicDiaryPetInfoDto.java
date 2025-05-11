package com.AniCare.demo.DTO.medical;


import com.AniCare.demo.constant.medical.PetSex;
import com.AniCare.demo.constant.medical.PetSpecies;
import com.AniCare.demo.entity.medical.Allergy;
import com.AniCare.demo.entity.medical.Disease;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDiaryPetInfoDto {

    private String petImage;
    private String petName;
    private String petBreed;
    private PetSex petSex;
    private int petAge;
    private PetSpecies petSpecies;
    private List<Disease> diseases;
    private List<Allergy> allergies;
}
