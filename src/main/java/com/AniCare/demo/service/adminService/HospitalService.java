package com.AniCare.demo.service.adminService;

import com.AniCare.demo.constant.admin.ClinicType;
import com.AniCare.demo.dto.admin.HospitalDto;
import com.AniCare.demo.entity.admin.Hospital;
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
    public void save(HospitalDto dto) {
        Hospital hospital = new Hospital();
        hospital.setHospitalName(dto.getHospitalName());
        hospital.setHospitalTel(dto.getHospitalTel());
        hospital.setHospitalImage(dto.getHospitalImage());
        hospital.setHospitalAddress(dto.getHospitalAddress());
        hospital.setDevice(dto.getDevice());
        hospital.setOperating(dto.getOperating());
        hospital.setClinicType(ClinicType.valueOf(dto.getClinicType()));

        // 수의사 ID가 있으면 연결


        hospitalRepository.save(hospital);
    }
}
