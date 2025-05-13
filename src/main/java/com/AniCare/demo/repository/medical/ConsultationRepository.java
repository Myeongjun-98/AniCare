package com.AniCare.demo.repository.medical;

import com.AniCare.demo.Dto.medical.UserConsultationListDto;
import com.AniCare.demo.entity.medical.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    /**
     * 로그인한 사용자(userName) 의 상담방 리스트를 DTO 로 조회
     */
    @Query("""
            SELECT new com.AniCare.demo.Dto.medical.UserConsultationListDto(
               c.id,
               c.user.userName,
               c.checkup.pet.petName,
               c.vet.vetName,
               false
            )
            FROM Consultation c
            WHERE c.user.userName = :username
            ORDER BY c.startedAt DESC
            """)
    List<UserConsultationListDto> findMyConsultations(@Param("username") String username);

}
