package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.MainPage.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface mediPetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByUserUserEmail(String userEmail);
}
