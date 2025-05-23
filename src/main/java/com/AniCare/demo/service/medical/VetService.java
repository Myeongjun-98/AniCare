package com.AniCare.demo.service.medical;

import com.AniCare.demo.Dto.admin.HospitalDto;
import com.AniCare.demo.Dto.medical.VetAverageReviewDto;
import com.AniCare.demo.Dto.medical.VetInfoDto;
import com.AniCare.demo.Dto.medical.VetInfoListDto;
import com.AniCare.demo.constant.medical.PetSpecies;
import com.AniCare.demo.entity.admin.Hospital;
import com.AniCare.demo.entity.medical.VetInfo;
import com.AniCare.demo.repository.admin.HospitalRepository;
import com.AniCare.demo.repository.medical.VetRepository;
import com.AniCare.demo.repository.medical.VetReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetService {
    private final VetRepository vetRepository;
    private final VetReviewRepository vetReviewRepository;
    private final HospitalRepository hospitalRepository;

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

    public List<VetInfoListDto> getVetsBySpeciesFirst(PetSpecies species) {

        // 별점순으로 정렬된 수의사 리스트 가져오기
        List<VetInfoListDto> all = getVetsWithRatings();

        // 진료 가능한 수의사 그룹
        List<VetInfoListDto> canCure = all.stream()
                .filter(v -> v.getCuringCapable().contains(species))
                .collect(Collectors.toList());

        // 진료가능 목록에 해당하는 동물이 없는 수의사 그룹
        List<VetInfoListDto> others = all.stream()
                .filter(o -> !o.getCuringCapable().contains(species))
                .collect(Collectors.toList());

        List<VetInfoListDto> reordered = new ArrayList<>(canCure.size() + others.size());
        reordered.addAll(canCure);
        reordered.addAll(others);

        return reordered;
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

    public VetInfoDto getVetDefaultInfo(String name) {

        VetInfo vetInfo = vetRepository.findByVetId(name);
        VetInfoDto vetInfoDto = VetInfoDto.createDto(vetInfo);

        return vetInfoDto;

    }

    public HospitalDto getHospitalInfo(String name) {
        VetInfo vetInfo = vetRepository.findByVetId(name);
        HospitalDto hospitalDto = new HospitalDto();
        if (vetInfo.getHospital() != null) {
            Hospital hospital = hospitalRepository.findById(vetInfo.getHospital().getId()).get();
            hospitalDto = HospitalDto.from(hospital);
        }


        return hospitalDto;
    }

}
