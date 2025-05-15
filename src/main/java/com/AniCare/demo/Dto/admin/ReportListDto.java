package com.AniCare.demo.Dto.admin;

import com.AniCare.demo.entity.MainPage.Report;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReportListDto {
    private Long reportId;
    private LocalDate reportDate;
    private Long boardId;
    private Long userId;
    private String reportType;

    public static ReportListDto fromEntity(Report report) {
        ReportListDto dto = new ReportListDto();

        dto.setReportId(report.getId()); // 신고 ID

        // 게시글 ID (board가 null일 수 있음)
        if (report.getBoard() != null) {
            dto.setBoardId(report.getBoard().getId());
        }

        // 신고자 ID (user가 null일 수 있음)
        if (report.getUser() != null) {
            dto.setUserId(report.getUser().getUserId());
        }

        dto.setReportDate(report.getReportDate()); // 신고 날짜
        dto.setReportType(report.getReportType().name()); // 신고 유형 (Enum → String)

        return dto;
    }
}
