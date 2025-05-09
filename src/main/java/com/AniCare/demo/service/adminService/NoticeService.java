package com.AniCare.demo.service.adminService;

import com.AniCare.demo.dto.admin.NoticeDto;
import com.AniCare.demo.dto.admin.NoticeListDto;
import com.AniCare.demo.entity.admin.Notice;
import com.AniCare.demo.constant.admin.NoticeCategory;
import com.AniCare.demo.repository.admin.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 공지 리스트 반환
     */
    public List<NoticeListDto> getNoticeList() {
        return noticeRepository.findAll().stream()
                .map(this::toListDto)
                .collect(Collectors.toList());
    }

    /**
     * Notice → NoticeListDto 변환
     */
    private NoticeListDto toListDto(Notice notice) {
        String summary = notice.getNoticeBody().length() <= 50
                ? notice.getNoticeBody()
                : notice.getNoticeBody().substring(0, 50) + "…";

        // ❗ NPE 방지 처리
        String categoryName = (notice.getNoticeCategory() != null)
                ? notice.getNoticeCategory().name()
                : "미지정";

        return new NoticeListDto(
                notice.getId(),
                notice.getNoticeTitle(),
                notice.getNoticeDate(),
                categoryName,
                summary,
                notice.getNoticeBody()
        );
    }

    /**
     * 공지 저장
     */
    public void save(NoticeDto dto) {
        Notice notice = new Notice();
        notice.setNoticeTitle(dto.getTitle());
        notice.setNoticeBody(dto.getBody());

        // ❗ Null 방지: 카테고리 없으면 기본값 설정
        notice.setNoticeCategory(dto.getCategory() != null ? dto.getCategory() : NoticeCategory.DEFAULT);

        notice.setNoticeDate(LocalDate.now());
        noticeRepository.save(notice);
    }

    /**
     * 공지 수정
     */
    public void update(Long id, NoticeDto dto) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 공지가 존재하지 않습니다: " + id)
        );

        notice.setNoticeTitle(dto.getTitle());
        notice.setNoticeBody(dto.getBody());

        // ❗ Null 방지
        notice.setNoticeCategory(dto.getCategory() != null ? dto.getCategory() : NoticeCategory.DEFAULT);

        noticeRepository.save(notice);
    }

    /**
     * 수정폼용 DTO 반환
     */
    public NoticeDto getNoticeForm(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 공지가 존재하지 않습니다: " + id)
        );

        return new NoticeDto(
                notice.getId(),notice.getNoticeTitle(),
                notice.getNoticeBody(),
                notice.getNoticeCategory() // null일 수 있음 → 폼에서 처리 가능
        );
    }
}
