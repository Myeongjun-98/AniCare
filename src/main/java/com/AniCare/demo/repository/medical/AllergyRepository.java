package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findByPet_id(Long PetId);
}
