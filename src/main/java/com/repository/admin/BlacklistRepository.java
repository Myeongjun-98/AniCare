package com.repository.admin;

import com.entity.admin.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlacklistRepository extends JpaRepository<Blacklist,Long> {
//    Optional<Blacklist> findByUserId(Long id);
}
