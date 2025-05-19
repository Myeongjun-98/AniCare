package com.AniCare.demo.Dto.admin;

import com.AniCare.demo.entity.admin.Hospital;
import com.AniCare.demo.entity.medical.VetInfo;
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

    private List<VetSimpleDto> vetList = new ArrayList<>(); // 수의사 ID + 이름
    private List<String> vetNameList = new ArrayList<>();   // 수의사 이름만 (출력용)

    // 엔티티 → DTO 변환
    public static HospitalDto from(Hospital hospital) {
        HospitalDto dto = new HospitalDto();

        dto.setId(hospital.getId());
        dto.setHospitalName(hospital.getHospitalName());
        dto.setHospitalTel(hospital.getHospitalTel());
        dto.setHospitalImage(hospital.getHospitalImage());
        dto.setDevice(hospital.getDevice());
        dto.setOperating(hospital.getOperating());
        dto.setHospitalAddress(hospital.getHospitalAddress());

        // 진료 유형
        dto.setClinicTypeList(
                hospital.getClinicTypes() != null
                        ? hospital.getClinicTypes().stream()
                        .map(Enum::name)
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );

        // 수의사 ID 목록
        dto.setVetInfoIdList(
                hospital.getVetInfos() != null
                        ? hospital.getVetInfos().stream()
                        .map(VetInfo::getId)
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );

        // 수의사 ID + 이름
        dto.setVetList(
                hospital.getVetInfos() != null
                        ? hospital.getVetInfos().stream()
                        .map(vet -> new VetSimpleDto(vet.getId(), vet.getVetName()))
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );

        // 수의사 이름 리스트 (Thymeleaf에서 listJoin용)
        dto.setVetNameList(
                hospital.getVetInfos() != null
                        ? hospital.getVetInfos().stream()
                        .map(VetInfo::getVetName)
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );

        return dto;
    }
}
