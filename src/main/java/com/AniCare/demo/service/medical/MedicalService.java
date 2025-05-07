package com.AniCare.demo.service.medical;

import com.AniCare.demo.Dto.medical.VetAverageReviewDto;
import com.AniCare.demo.Dto.medical.VetInfoListDto;
import com.AniCare.demo.repository.medical.VetRepository;
import com.AniCare.demo.repository.medical.VetReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalService {

    @Autowired
    private VetRepository vetRepository;
    @Autowired
    private VetReviewRepository vetReviewRepository;

    // 수의사 리스트 별점순으로 정렬하기
    public List<VetInfoListDto> getVetsWithRatings() {

        // 1) 엔티티 → DTO 매핑 (병원·컬렉션까지 이미 fetch 됐음)
        List<VetInfoListDto> vets = vetRepository.findAllWithHospitalAndCuringCapable().stream()
                .map(v -> new VetInfoListDto(
                        v.getId(),
                        v.getVetName(),
                        v.getHospital().getHospitalName(),
                        v.getProfileImage(),
                        v.getCuringCapable(),
                        0.0                          // averageRating 은 나중에 세팅
                ))
                .collect(Collectors.toList());

        // 2) 수의사별 평균 별점 조회 → Map<vetInfoId, avgRating>
        Map<Long, Double> ratingMap = vetReviewRepository.findAverageRatings().stream()
                .collect(Collectors.toMap(
                        VetAverageReviewDto::getVetInfoId,
                        VetAverageReviewDto::getAverageRating
                ));

        // 3) DTO 에 평균 별점 세팅
        vets.forEach(dto ->
                dto.setAverageRating(
                        ratingMap.getOrDefault(dto.getVetInfoId(), 0.0)
                )
        );

        // 4) 내림차순 정렬
        vets.sort(
                Comparator.comparingDouble(VetInfoListDto::getAverageRating)
                        .reversed()
        );

        return vets;
    }
}


