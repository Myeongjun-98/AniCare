package com.AniCare.demo.repository.MainPage;

import com.AniCare.demo.entity.MainPage.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // 사용자가 작성한 신고내용의 리스트를 관리자에게 띄우기 위한 쿼리 동작 메서드
//    List<Report> findAllByOrderByIdDesc();

    // 사용자가 작성한 신고내용의 상세보기를 위한 쿼리 동작 메서드
//    Report findByReportId(Long id);
}
