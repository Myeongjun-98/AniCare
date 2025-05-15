package com.AniCare.demo.dto.admin;


import com.AniCare.demo.entity.admin.Hospital;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDto {
    private Long id;
    private String hospitalName;
    private String hospitalTel;
    private String hospitalImage;
    private List<Long> vetInfoIdList; // 수의사 ID 목록
    private String device;
    private String operating;
    private List<String> clinicTypeList; // 다중 선택용 진료 유형
    private String hospitalAddress;

    // 엔티티 → DTO 변환
    public static HospitalDto from(Hospital hospital) {
        return new HospitalDto(
                hospital.getId(),
                hospital.getHospitalName(),
                hospital.getHospitalTel(),
                hospital.getHospitalImage(),
                hospital.getVetInfos() != null
                        ? hospital.getVetInfos().stream()
                        .map(vet -> vet.getId())
                        .collect(Collectors.toList())
                        : new ArrayList<>(),
                hospital.getDevice(),
                hospital.getOperating(),
                hospital.getClinicTypes() != null
                        ? hospital.getClinicTypes().stream()
                        .map(Enum::name)
                        .collect(Collectors.toList())
                        : new ArrayList<>(),
                hospital.getHospitalAddress()
        );
    }
}
