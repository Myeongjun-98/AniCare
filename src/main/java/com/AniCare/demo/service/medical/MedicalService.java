package com.AniCare.demo.service.medical;

import com.AniCare.demo.DTO.medical.ClinicDiaryListDto;
import com.AniCare.demo.repository.medical.ClinicDiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalService {

    private final ClinicDiaryRepository clinicDiaryRepository;

    @Transactional(readOnly = true)
    public List<ClinicDiaryListDto> findClinicDiariesByPetId(Long petId) {

        return clinicDiaryRepository.findAllByPetId(petId).stream()
                .map(
                        c -> new ClinicDiaryListDto(
                                c.getId(),
                                c.getBoard().getBoardWriteDate(),
                                c.getBoard().getBoardTitle(),
                                c.getBoard().getBoardHit(),
                                c.getStatus()
                        )
                ).collect(Collectors.toList());
    }
}


