package com.AniCare.demo.service.adminService;

import com.AniCare.demo.constant.MainPage.Authorization;
import com.AniCare.demo.dto.admin.MasterAccountDto;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.admin.MasterAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterAccountService {

    private final MasterAccountRepository masterAccountRepository;
    private final UserRepository userRepository;

    /**
     * 모든 계정 목록 반환
     */
    public List<MasterAccountDto> findAll() {
        List<User> accounts = userRepository.findAll();

        return accounts.stream()
                .map(account -> new MasterAccountDto(
                        account.getUserId(),
                        account.getUserName(),
                        account.getAuthorization()
                ))
                .collect(Collectors.toList());
    }

    /**
     * 사용자 권한 변경
     */
    public void updateRole(Long userId, Authorization role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다: " + userId));
        user.setAuthorization(role);
        userRepository.save(user);
    }
}
