package com.repository.admin;

import com.entity.admin.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {
//    Optional<Notice> findById(Long NoticeId);
}
