package com.repository.MainPage;

import com.entity.entity.MainPage.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // 반려동물 등록 및 수정을 위한 쿼리 동작 메서드
    Pet findByPetId(Long id);

    // 사용자가 키우는 반려동물 정보를 가져오기 위한 쿼리 동작 메서드
    List<Pet> findAllByUserId(Long id);
}