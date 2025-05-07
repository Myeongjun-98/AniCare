package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.admin.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {
//    Optional<Notice> findById(Long NoticeId);
}
