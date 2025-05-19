package com.AniCare.demo.repository.medical;

import com.AniCare.demo.Dto.medical.ConsultationChatListDto;
import com.AniCare.demo.entity.medical.ConsultationChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationChatRepository extends JpaRepository<ConsultationChat, Long> {

    //채팅 메시지 조회
    List<ConsultationChat> findAllByConsultationId(Long consultationId);

    // 안읽은 메시지가 있는지 조회(단수)
    boolean existsByConsultation_IdAndReadFlagFalse(Long roomId);

    // 안 읽은 메시지가 있는지 조회(복수)
    boolean existsByConsultationIdAndReadFlagFalse(Long consultationId);

}
