package com.AniCare.demo.repository.MainPage;

import com.AniCare.demo.Dto.mainpage.EnquiryListDto;
import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.MainPage.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {

    Page<Enquiry> findAll(Pageable pageable);


    // 사용자가 작성했던 문의사항 목록을 보여주기 위한 쿼리 동작 메서드
    List<Enquiry> findByUserOrderByIdDesc(User user);

    // 문의사항 상세보기를 위한 쿼리 동작 메서드
//    Enquiry findByEnquiryId(Long id);
}
