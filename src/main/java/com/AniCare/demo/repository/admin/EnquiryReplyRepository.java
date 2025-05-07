package com.AniCare.demo.repository.admin;

import com.AniCare.demo.entity.admin.EnquiryReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryReplyRepository extends JpaRepository<EnquiryReply, Long> {
    List<EnquiryReply> findByEnquiryId(Long enquiryId);
}
