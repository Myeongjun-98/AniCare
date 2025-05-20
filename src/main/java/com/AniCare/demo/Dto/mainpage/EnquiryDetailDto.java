package com.AniCare.demo.Dto.mainpage;


import com.AniCare.demo.constant.MainPage.EnquiryType;
import com.AniCare.demo.entity.MainPage.Enquiry;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
public class EnquiryDetailDto {

        
    private EnquiryType enquiryType;
    private String enquiryTitle;
    private String enquiryContent;
    private LocalDate enquiryDate;
    private String enquiryFile;

    public EnquiryDetailDto from (Enquiry enquiry){
        EnquiryDetailDto enquiryDetailDto = new EnquiryDetailDto();

        return enquiryDetailDto;
    }
}
