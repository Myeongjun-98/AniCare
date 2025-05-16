package com.AniCare.demo.service.adminService;

import com.AniCare.demo.constant.admin.ClinicType;
import com.AniCare.demo.Dto.admin.HospitalDto;
import com.AniCare.demo.entity.admin.Hospital;
import com.AniCare.demo.entity.medical.VetInfo;
import com.AniCare.demo.repository.admin.HospitalRepository;
import com.AniCare.demo.repository.medical.VetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final VetRepository vetInfoRepository;

    /**
     * 병원 전체 조회
     */
    public List<HospitalDto> findAll() {
        return hospitalRepository.findAll().stream()
                .map(HospitalDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 병원 단건 조회 (수정용)
     */
    public HospitalDto findById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원이 존재하지 않습니다."));
        return HospitalDto.from(hospital);
    }

    /**
     * 병원 등록 및 수의사 연결
     */
    public void save(HospitalDto dto) {
        Hospital hospital = new Hospital();
        hospital.setHospitalName(dto.getHospitalName());
        hospital.setHospitalTel(dto.getHospitalTel());
        hospital.setHospitalImage(dto.getHospitalImage());
        hospital.setHospitalAddress(dto.getHospitalAddress());
        hospital.setDevice(dto.getDevice());
        hospital.setOperating(dto.getOperating());

        // clinicTypeList → List<ClinicType>
        if (dto.getClinicTypeList() != null && !dto.getClinicTypeList().isEmpty()) {
            List<ClinicType> clinicTypes = dto.getClinicTypeList().stream()
                    .map(ClinicType::valueOf)
                    .collect(Collectors.toList());
            hospital.setClinicTypes(clinicTypes);
        } else {
            hospital.setClinicTypes(new ArrayList<>());
        }

        // 병원 저장
        hospital = hospitalRepository.save(hospital);

        // 수의사 병원 연결
        if (dto.getVetInfoIdList() != null) {
            List<VetInfo> vetInfos = vetInfoRepository.findAllById(dto.getVetInfoIdList());
            for (VetInfo vet : vetInfos) {
                vet.setHospital(hospital);
                vetInfoRepository.save(vet);
            }
        }
    }

    /**
     * 병원 삭제 (연결된 수의사 참조 먼저 해제)
     */
    @Transactional
    public void delete(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원이 존재하지 않습니다."));

        // 연결된 수의사들의 hospital 참조 해제
        if (hospital.getVetInfos() != null) {
            for (VetInfo vet : hospital.getVetInfos()) {
                vet.setHospital(null);
            }
        }

        hospitalRepository.delete(hospital);
    }

    /**
     * 병원 정보 수정
     */
    @Transactional
    public void update(HospitalDto dto) {
        Hospital hospital = hospitalRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 병원이 존재하지 않습니다."));

        hospital.setHospitalName(dto.getHospitalName());
        hospital.setHospitalTel(dto.getHospitalTel());
        hospital.setHospitalImage(dto.getHospitalImage());
        hospital.setHospitalAddress(dto.getHospitalAddress());
        hospital.setDevice(dto.getDevice());
        hospital.setOperating(dto.getOperating());

        if (dto.getClinicTypeList() != null) {
            List<ClinicType> clinicTypes = dto.getClinicTypeList().stream()
                    .map(ClinicType::valueOf)
                    .collect(Collectors.toList());
            hospital.setClinicTypes(clinicTypes);
        }
    }
}
