package com.AniCare.demo.service.adminService;

import com.AniCare.demo.DTO.admin.MasterAccountDto;
import com.AniCare.demo.entity.admin.MasterAccount;
import com.AniCare.demo.repository.admin.MasterAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterAccountService {

    private final MasterAccountRepository masterAccountRepository;

    public List<MasterAccountDto> findAll() {
        List<MasterAccount> accounts = masterAccountRepository.findAll();

        // ✅ 로그 출력 추가
        System.out.println("📌 DB에서 불러온 계정 수: " + accounts.size());
        for (MasterAccount account : accounts) {
            System.out.println(" - ID: " + account.getId() + ", 로그인ID: " + account.getLoginId());
        }

        return accounts.stream()
                .map(account -> new MasterAccountDto(
                        account.getId(),
                        account.getLoginId(),
                        account.getName(),
                        account.getRole().toString(),
                        account.getCreateDate()
                ))
                .collect(Collectors.toList());
    }
}
