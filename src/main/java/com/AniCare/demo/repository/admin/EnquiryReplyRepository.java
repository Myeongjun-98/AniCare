package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.MainPage.Enquiry;
import com.AniCare.demo.entity.admin.EnquiryReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EnquiryReplyRepository extends JpaRepository<EnquiryReply,Long> {
        List<EnquiryReply> findByEnquiryId(Long enquiryId);
        Optional<EnquiryReply> findByEnquiry(Enquiry enquiry);

}
