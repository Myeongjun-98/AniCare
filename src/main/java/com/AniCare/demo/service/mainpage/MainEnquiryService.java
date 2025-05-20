package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.EnquiryListDto;
import com.AniCare.demo.Dto.mainpage.EnquiryWriteDto;
import com.AniCare.demo.constant.MainPage.EnquiryType;
import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.repository.MainPage.EnquiryRepository;
import com.AniCare.demo.repository.MainPage.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainEnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final UserRepository userRepository;

    public List<EnquiryListDto> getUserEnquiryList(User user) {
        return enquiryRepository.findByUserOrderByIdDesc(user).stream()
                .map(EnquiryListDto::from) // ✅ static 메서드 사용
                .toList(); // Java 16 이상. Java 8~11은 .collect(Collectors.toList())
    }

    public Page<Enquiry> getAllEnquiries(Pageable pageable) {

        return enquiryRepository.findAll(pageable);
    }

    public void enquirysave(@Valid EnquiryWriteDto enquiryWriteDto, List<MultipartFile> multipartFileList, String email) {
        Enquiry enquiry = new Enquiry();

        //로그인한 사용자 객체 불러오기
        User user = userRepository.findByUserEmail(email).orElse(null);

        //todo-1.DTO(get) -> Entity(set)

        //enquiryType
        EnquiryType enquiryType = EnquiryType.valueOf(String.valueOf(enquiryWriteDto.getEnquiryType()));

        enquiry.setUser(user);
        enquiry.setEnquiryTitle(enquiryWriteDto.getEnquiryTitle());
        enquiry.setEnquiryContent(enquiryWriteDto.getEnquiryContent());
        enquiry.setEnquiryType(enquiryWriteDto.getEnquiryType());
        enquiry.setEnquiryDate(enquiryWriteDto.getEnquiryDate());
        enquiry.setEnquiryFile(enquiryWriteDto.getEnquiryFile());

        //todo-2.repo.save(entity)
        enquiryRepository.save(enquiry);
    }
}
