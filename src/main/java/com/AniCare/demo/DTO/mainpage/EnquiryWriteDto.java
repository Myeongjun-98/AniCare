package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.constant.MainPage.EnquiryType;
import com.AniCare.demo.entity.MainPage.Enquiry;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EnquiryWriteDto {

    private EnquiryType enquiryType;
    private String enquiryTitle;
    private String enquiryContent;
    private LocalDate enquiryDate;
    private String enquiryFile;




}
