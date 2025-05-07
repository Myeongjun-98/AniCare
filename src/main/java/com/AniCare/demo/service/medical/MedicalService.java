package com.AniCare.demo.service.medical;

import com.AniCare.demo.Dto.medical.VetInfoListDto;
import com.AniCare.demo.entity.medical.VetInfo;
import com.AniCare.demo.entity.medical.VetReview;
import com.AniCare.demo.repository.medical.VetRepository;
import com.AniCare.demo.repository.medical.VetReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalService {

    @Autowired
    private VetRepository vetRepository;
    @Autowired
    private VetReviewRepository vetReviewRepository;

    // 수의사 리스트 별점순으로 정렬하기
    public List<VetInfoListDto> getAllVets() {

        List<VetInfo> vetList = vetRepository.findAllWithCuringCapable();
        List<VetReview> vetAverageReviewDtoList = vetReviewRepository.findAverageRatings();

        // 2) DTO로 변환
        return vetList.stream()
                .map(v ->
                        new VetInfoListDto(
                                v.getId(),
                                v.getVetName(),
                                v.getHospital().getHospitalName(),
                                v.getProfileImage(),
                                v.getCuringCapable()         // 엔티티가 가져온 List<PetSpecies> 을 그대로 넣는다
                        )
                )
                .collect(Collectors.toList());
    }
}


