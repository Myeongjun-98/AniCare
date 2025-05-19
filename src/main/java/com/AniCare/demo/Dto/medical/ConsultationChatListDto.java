package com.AniCare.demo.Dto.medical;

import com.AniCare.demo.constant.MainPage.SenderType;
import com.AniCare.demo.entity.medical.ConsultationChat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationChatListDto {

    private Long messageId;           // 메시지 고유 ID
    private Long consultationId;      // 어떤 상담방 소속인지
    private SenderType senderType;    // ENUM { USER, VET }
    private Long senderId;            // 보내는 사람의 DB PK
    private String senderName;        // 보내는 사람 이름 (optional)
    private String content;           // 메시지 본문
    private LocalDateTime sentAt;     // 전송 시각
    private boolean readFlag;             // 읽음 여부


    public static ConsultationChatListDto from(ConsultationChat consultationChat) {
        ConsultationChatListDto consultationChatListDto = new ConsultationChatListDto();

        consultationChatListDto.setMessageId(consultationChat.getId());
        consultationChatListDto.setConsultationId(consultationChat.getConsultation().getId());
        consultationChatListDto.setSentAt(consultationChat.getSendAt());
        consultationChatListDto.setContent(consultationChat.getContent());
        consultationChatListDto.setReadFlag(consultationChat.isReadFlag());

        //senderType, senderId, senderName는 Service로 직접 넣을 것.

        return consultationChatListDto;
    }
}
