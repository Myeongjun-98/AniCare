package com.entity.admin;
import com.entity.MainPage.Enquiry;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class EnquiryReply {
    @Id@GeneratedValue(strategy = IDENTITY)
    private Long replyId;
    @OneToOne
    private Enquiry enquiry;
    private String content;
    private LocalDate createDate;


}
