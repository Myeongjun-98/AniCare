package com.AniCare.demo.repository.medical;

import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.medical.ClinicDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicDiaryRepository extends JpaRepository<ClinicDiary, Long> {

    // 펫 아이디로 진료수첩 리스트 불러오기
    @Query("""
            SELECT DISTINCT c
            FROM ClinicDiary c
                JOIN FETCH c.board
                JOIN FETCH c.pet
                LEFT JOIN FETCH c.hospital
            WHERE c.pet.id = :id
            """)
    List<ClinicDiary> findAllByPetId(@Param("petId") Long id);

    // (임시) 유저네임으로 펫 리스트 불러오기??
    List<Pet> findByUser_UserName(String userName);

    // 진료수첩 정보 불러오기
    @Query("""
            SELECT DISTINCT c
            FROM ClinicDiary c
                JOIN FETCH c.board
                JOIN FETCH c.pet p
                LEFT JOIN FETCH p.disease   d
                LEFT JOIN FETCH p.allergy   a
                LEFT JOIN FETCH c.hospital
                WHERE c.id = :id
            """)
    Optional<ClinicDiary> findByClinicDiaryId(@Param("id") Long id);
}
