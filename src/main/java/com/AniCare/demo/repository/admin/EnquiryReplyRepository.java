package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.admin.EnquiryReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnquiryReplyRepository extends JpaRepository<EnquiryReply, Long> {
    List<EnquiryReply> findAllByEnquiryId(Long enquiryId);

        Optional<EnquiryReply> findByEnquiry(Enquiry enquiry);

    Optional<EnquiryReply> findByEnquiryId(Long id);
}
