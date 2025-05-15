package com.AniCare.demo.service.adminService;

import com.AniCare.demo.Dto.admin.HospitalDto;
import com.AniCare.demo.repository.admin.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public List<HospitalDto> findAll() {
        return hospitalRepository.findAll().stream()
                .map(hospital -> new HospitalDto(
                        hospital.getId(),
                        hospital.getHospitalName(),
                        hospital.getHospitalTel(),
                        hospital.getHospitalImage(),
                        hospital.getVetInfo() != null ? hospital.getVetInfo().getId() : null,
                        hospital.getDevice(),
                        hospital.getOperating(),
                        hospital.getClinicType() != null ? hospital.getClinicType().toString() : null,
                        hospital.getHospitalAddress()
                ))
                .collect(Collectors.toList());
    }
}
