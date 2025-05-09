package com.AniCare.demo.service.adminService;

import com.AniCare.demo.dto.admin.MasterAccountDto;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.UserRepository;
import com.AniCare.demo.repository.admin.MasterAccountRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class MasterAccountService {

    private final MasterAccountRepository masterAccountRepository;
    private  final UserRepository userRepository;

    public List<MasterAccountDto> findAll() {
        List<User> accounts = userRepository.findAll();


        System.out.println(" DB에서 불러온 계정 수: " + accounts.size());
        for (User account : accounts) {
            System.out.println(" - ID: " + account.getId() + ", 로그인ID: " + account.getLoginId());
        }

        return accounts.stream()
                .map(account -> new MasterAccountDto(
                        account.getId(),
                        account.getLoginId(),
                        account.getUserName(),
                        account.getAuthorization()

                ))
                .collect(Collectors.toList());
    }
}
