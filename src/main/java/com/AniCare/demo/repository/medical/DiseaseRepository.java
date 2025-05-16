package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    List<Disease> findByPet_id(Long PetId);
}
