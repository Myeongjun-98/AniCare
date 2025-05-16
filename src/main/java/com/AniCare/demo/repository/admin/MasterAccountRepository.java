package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.admin.MasterAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterAccountRepository extends JpaRepository<MasterAccount, Long> {
}
