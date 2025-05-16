package com.AniCare.demo.repository.medical;

import com.AniCare.demo.Dto.medical.ConsultationChatListDto;
import com.AniCare.demo.entity.medical.ConsultationChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationChatRepository extends JpaRepository<ConsultationChat, Long> {

    @Query("""
              SELECT new com.AniCare.demo.Dto.medical.ConsultationChatListDto(
                cm.id,
                c.id,
                case
                  when cm.senderUser is not null then com.AniCare.demo.constant.MainPage.SenderType.USER else com.AniCare.demo.constant.MainPage.SenderType.VET
                end,
                coalesce(cm.senderUser.id, cm.senderVet.id),
                coalesce(cm.senderUser.userName, cm.senderVet.vetName),
                cm.content,
                cm.sendAt,
                cm.readFlag
              )
              FROM ConsultationChat cm
                JOIN cm.consultation c
              WHERE c.id = :consultationId
              ORDER BY cm.sendAt
            """)
    List<ConsultationChatListDto> findMessagesByConsultation(Long consultationId);


    // 안읽은 메시지가 있는지 조회(단수)
    boolean existsByConsultation_IdAndReadFlagFalse(Long roomId);

    // 안 읽은 메시지가 있는지 조회(복수)
    boolean existsByConsultationIdAndReadFlagFalse(Long consultationId);

}
