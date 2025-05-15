package com.AniCare.demo.service.adminService;

import com.AniCare.demo.Dto.admin.EnquiryReplyViewDto;
import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.admin.EnquiryReply;
import com.AniCare.demo.repository.MainPage.EnquiryRepository;
import com.AniCare.demo.repository.admin.EnquiryReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnquiryService {

    private final EnquiryReplyRepository enquiryReplyRepository;
    private final EnquiryRepository enquiryRepository;

    /**
     * 문의에 대한 답변 저장 또는 수정
     */
    @Transactional
    public void reply(Long enquiryId, String replyContent) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new IllegalArgumentException("문의가 존재하지 않습니다: " + enquiryId));

        enum EnquiryStatus {
            처리대기,
            처리완료
        }

        // 이미 답변이 있는지 확인
        Optional<EnquiryReply> optionalReply = enquiryReplyRepository.findByEnquiry(enquiry);

        EnquiryReply reply = optionalReply.orElseGet(EnquiryReply::new); // 없으면 새로 생성
        reply.setEnquiry(enquiry);
        reply.setContent(replyContent);
        reply.setCreateDate(LocalDate.now());

        enquiryReplyRepository.save(reply);
    }

    /**
     * 문의 + 답변 리스트 조회
     */
    @Transactional(readOnly = true)
    public List<EnquiryReplyViewDto> findAll() {
        List<EnquiryReplyViewDto> enquiryReplyViewDtos = new ArrayList<>();
        List<Enquiry> enquiries = enquiryRepository.findAll();

        for (Enquiry enquiry : enquiries) {
            EnquiryReply enquiryReply = enquiryReplyRepository.findByEnquiryId(enquiry.getId())
                    .orElse(new EnquiryReply());

            EnquiryReplyViewDto dto = EnquiryReplyViewDto.of(enquiryReply, enquiry);
            enquiryReplyViewDtos.add(dto);
        }

        return enquiryReplyViewDtos;
    }
}
