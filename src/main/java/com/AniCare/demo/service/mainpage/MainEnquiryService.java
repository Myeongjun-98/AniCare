package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.EnquiryListDto;
import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.EnquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainEnquiryService {

    private final EnquiryRepository enquiryRepository;

    public List<EnquiryListDto> getUserEnquiryList(User user) {
        return enquiryRepository.findByUserOrderByIdDesc(user).stream()
                .map(EnquiryListDto::from) // ✅ static 메서드 사용
                .toList(); // Java 16 이상. Java 8~11은 .collect(Collectors.toList())
    }

    public Page<Enquiry> getAllEnquiries(Pageable pageable) {

        return enquiryRepository.findAll(pageable);
    }
}
