package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.medical.ClinicDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicDiaryRepository extends JpaRepository<ClinicDiary, Long> {

    @Query("""
            SELECT DISTINCT c
            FROM ClinicDiary c
                JOIN FETCH c.board
                JOIN FETCH c.pet
                LEFT JOIN FETCH c.hospital
            WHERE c.pet.id = :id
            """)
    List<ClinicDiary> findAllByPetId(@Param("petId") Long id);

}
