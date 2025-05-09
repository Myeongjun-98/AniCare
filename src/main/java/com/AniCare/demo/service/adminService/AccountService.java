package com.AniCare.demo.service.adminService;

import com.AniCare.demo.dto.admin.MasterAccountDto;
import com.AniCare.demo.repository.admin.MasterAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final MasterAccountRepository masterAccountRepository;

    public List<MasterAccountDto> findAll() {
        return masterAccountRepository.findAll().stream()
                .map(MasterAccountDto::fromEntity) // 정적 팩토리 메서드로 변환
                .collect(Collectors.toList());
    }
}
