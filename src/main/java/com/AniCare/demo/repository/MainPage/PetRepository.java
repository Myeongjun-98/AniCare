package com.AniCare.demo.repository.MainPage;

import com.AniCare.demo.entity.MainPage.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Pet findByUserUserId(Long id);

//     반려동물 등록 및 수정을 위한 쿼리 동작 메서드


}