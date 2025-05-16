package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.constant.MainPage.EnquiryStatus;
import com.AniCare.demo.constant.MainPage.EnquiryType;
import com.AniCare.demo.entity.MainPage.Enquiry;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EnquiryListDto {

    private Long id;        // ✅ 상세보기용 ID 필드 추가
    private String userName;
    private EnquiryType enquiryType;
    private String enquiryTitle;
    private LocalDate enquiryDate;
    private EnquiryStatus enquiryStatus;

    // ✅ static 변환 메서드
    public static EnquiryListDto from(Enquiry enquiry) {
        EnquiryListDto dto = new EnquiryListDto();
        UserDetailDto userDetailDto = new UserDetailDto();
        dto.setUserName(userDetailDto.getUserName());
        dto.setId(enquiry.getId());                        // ✅ ID 설정 추가
        dto.setEnquiryTitle(enquiry.getEnquiryTitle());
        dto.setEnquiryType(enquiry.getEnquiryType());
        dto.setEnquiryDate(enquiry.getEnquiryDate());
        dto.setEnquiryStatus(enquiry.getStatus());
        return dto;
    }

    private void setUserName(String userName) {

    }
}
