package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.admin.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    Optional<Blacklist> findByUserId(Long id);
}
