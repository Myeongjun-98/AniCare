package com.AniCare.demo.service.adminService;

import com.AniCare.demo.dto.admin.ReportListDto;
import com.AniCare.demo.repository.admin.ReportListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportListService {

    private final ReportListRepository reportListRepository;

    public List<ReportListDto> findAll() {
        return reportListRepository.findAll().stream()
                .map(ReportListDto::fromEntity)
                .collect(Collectors.toList());
    }
}
