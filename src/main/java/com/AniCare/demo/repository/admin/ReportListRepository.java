package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.MainPage.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportListRepository extends JpaRepository<Report, Long> {
}
