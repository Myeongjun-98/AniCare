package com.AniCare.demo.DTO.admin;

import com.AniCare.demo.entity.admin.MasterAccount;

import java.time.LocalDate;

public class MasterAccountDto {
    private Long id;
    private String loginId;
    private String name;
    private String role;
    private LocalDate createDate;

    public MasterAccountDto(Long id, String loginId, String name, String role, LocalDate createDate) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.role = role;
        this.createDate = createDate;
    }

    public Long getId() { return id; }
    public String getLoginId() { return loginId; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public LocalDate getCreateDate() { return createDate; }

    // ✅ 정적 팩토리 메서드 추가
    public static MasterAccountDto fromEntity(MasterAccount account) {
        return new MasterAccountDto(
                account.getId(),
                account.getLoginId(),
                account.getName(),
                account.getRole().name(),
                account.getCreateDate()
        );
    }
}
