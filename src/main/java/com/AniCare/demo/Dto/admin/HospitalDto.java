//package com.AniCare.demo.Dto.admin;
//
//import com.AniCare.demo.entity.admin.Hospital;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor // ← 이 줄 추가
//public class HospitalDto {
//    private Long id;
//    private String hospitalName;
//    private String hospitalTel;
//    private String hospitalImage;
//    private Long vetInfoId;
//    private String device;
//    private String operating;
//    private String clinicType;
//    private String hospitalAddress;
//
//    public static HospitalDto from(Hospital hospital) {
//        return new HospitalDto(
//                hospital.getId(),
//                hospital.getHospitalName(),
//                hospital.getHospitalTel(),
//                hospital.getHospitalImage(),
//                hospital.getVetInfo() != null ? hospital.getVetInfo().getId() : null,
//                hospital.getDevice(),
//                hospital.getOperating(),
//                hospital.getClinicType() != null ? hospital.getClinicType().name() : null,
//                hospital.getHospitalAddress()
//        );
//    }
//}
