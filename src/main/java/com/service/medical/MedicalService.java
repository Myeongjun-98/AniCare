package com.service.medical;

import com.repository.medical.VetRepository;
import com.repository.medical.VetReviewRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@NoArgsConstructor
public class MedicalService {

    @Autowired
    private VetRepository vetRepository;
    @Autowired
    private VetReviewRepository vetReviewRepository;

    // 수의사 리스트 별점순으로 정렬하기
    

}
