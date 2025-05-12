package com.AniCare.demo.repository.medical;

import com.AniCare.demo.Dto.medical.ClinicDiaryListDto;
import com.AniCare.demo.entity.medical.ClinicDiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicDiaryRepository extends JpaRepository<ClinicDiary, Long> {

    // 펫 아이디로 진료수첩 리스트 불러오기

    /**
     * 반려동물(petId) 기준 진료수첩 목록을 DTO로 페이징 조회
     */
    @Query("""
              SELECT new com.AniCare.demo.Dto.medical.ClinicDiaryListDto(
                c.id,
                c.clinicDiaryRecordDate,
                c.board.boardTitle,
                c.board.boardHit,
                c.status
              )
              FROM ClinicDiary c
              WHERE c.pet.id = :petId
              ORDER BY c.clinicDiaryRecordDate DESC
            """)
    Page<ClinicDiaryListDto> findAllByPetId(
            @Param("petId") Long petId,
            Pageable pageable
    );

    // 진료수첩 정보 불러오기
    @Query("""
            SELECT DISTINCT c
            FROM ClinicDiary c
                JOIN FETCH c.board
                JOIN FETCH c.pet
                LEFT JOIN FETCH c.hospital
                WHERE c.id = :id
            """)
    Optional<ClinicDiary> findByClinicDiaryId(@Param("id") Long id);
}
