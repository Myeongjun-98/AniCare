package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.constant.MainPage.EnquiryStatus;
import com.AniCare.demo.constant.MainPage.EnquiryType;
import com.AniCare.demo.entity.MainPage.Enquiry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.time.LocalDate;

@Getter
@Setter
public class EnquiryListDto {

    private EnquiryType enquiryType;
    private String enquiryTitle;
    private LocalDate enquiryDate;
    private EnquiryStatus enquiryStatus;

    public EnquiryListDto from (Enquiry enquiry){
        EnquiryListDto enquiryListDto = new EnquiryListDto();

        enquiryListDto.setEnquiryTitle(enquiry.getEnquiryTitle());
        enquiryListDto.setEnquiryType(enquiry.getEnquiryType());
        enquiryListDto.setEnquiryDate(enquiry.getEnquiryDate());
        enquiryListDto.setEnquiryStatus(enquiry.getEnquiryStatus());

        return enquiryListDto;
    }
}
