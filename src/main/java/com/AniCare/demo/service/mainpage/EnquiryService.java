package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.EnquiryListDto;
import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.repository.MainPage.EnquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnquiryService {
    
    private final EnquiryRepository enquiryRepository;

    public List<Enquiry>getAllEnquiryList(){
        return enquiryRepository.findAllByOrderByIdDesc();


    }


}
