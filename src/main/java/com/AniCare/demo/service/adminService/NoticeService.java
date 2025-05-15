package com.AniCare.demo.service.adminService;

import com.AniCare.demo.Dto.admin.NoticeDto;
import com.AniCare.demo.Dto.admin.NoticeListDto;
import com.AniCare.demo.entity.admin.Notice;
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

    public List<NoticeListDto> getNoticeList() {
        return noticeRepository.findAll().stream()
                .map(this::toListDto)
                .collect(Collectors.toList());
    }

    private NoticeListDto toListDto(Notice notice) {
        String summary = notice.getNoticeBody().length() <= 50
                ? notice.getNoticeBody()
                : notice.getNoticeBody().substring(0, 50) + "…";

        return new NoticeListDto(
                notice.getId(),
                notice.getNoticeTitle(),
                notice.getNoticeDate(),
                notice.getNoticeCategory().name(),
                summary,
                notice.getNoticeBody() //
        );
    }

    public void save(NoticeDto dto) {
        Notice notice = new Notice();
        notice.setNoticeTitle(dto.getTitle());
        notice.setNoticeBody(dto.getBody());
        notice.setNoticeCategory(dto.getCategory());
        notice.setNoticeDate(LocalDate.now());
        noticeRepository.save(notice);
    }

    public void update(Long id, NoticeDto dto) {
        Notice notice = noticeRepository.findById(id).orElseThrow();
        notice.setNoticeTitle(dto.getTitle());
        notice.setNoticeBody(dto.getBody());
        notice.setNoticeCategory(dto.getCategory());
        noticeRepository.save(notice);
    }

    public NoticeDto getNoticeForm(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow();
        return new NoticeDto(
                notice.getNoticeTitle(),
                notice.getNoticeBody(),
                notice.getNoticeCategory()
        );
    }
}
