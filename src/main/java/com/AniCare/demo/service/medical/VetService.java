package com.AniCare.demo.service.medical;

import com.AniCare.demo.DTO.medical.VetAverageReviewDto;
import com.AniCare.demo.DTO.medical.VetInfoDto;
import com.AniCare.demo.DTO.medical.VetInfoListDto;
import com.AniCare.demo.entity.medical.VetInfo;
import com.AniCare.demo.repository.medical.VetRepository;
import com.AniCare.demo.repository.medical.VetReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VetService {
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

    // 수의사 상세정보 불러오기
    public VetInfoDto findVetById(Long id) {

        // Optional 정보 가져오기
        VetInfo v = vetRepository.findWithDetailById(id).orElseThrow(() -> new NoSuchElementException("수의사 정보가 없습니다. id=" + id));

        // 평균 계산
        double avg = vetReviewRepository.findAverageRatings().stream()
                .filter(a -> a.getVetInfoId().equals(v.getId()))
                .mapToDouble(VetAverageReviewDto::getAverageRating)
                .findFirst()
                .orElse(0);

        // DTO 에 값 주입
        return new VetInfoDto(
                v.getId(),
                v.getProfileImage(),
                v.getVetName(),
                v.getHospital().getHospitalName(),
                v.getCuringCapable(),
                v.getOnWorkTime(),
                v.getOffWorkTime(),
                avg
        );

    }

}
