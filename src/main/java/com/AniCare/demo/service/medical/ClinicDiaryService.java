package com.AniCare.demo.service.medical;

import com.AniCare.demo.Dto.medical.ClinicDiaryDto;
import com.AniCare.demo.Dto.medical.ClinicDiaryListDto;
import com.AniCare.demo.Dto.medical.ClinicDiaryPetInfoDto;
import com.AniCare.demo.Dto.medical.ClinicDiarySetDto;
import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.MainPage.Pet;
import com.AniCare.demo.entity.MainPage.User;
import com.AniCare.demo.entity.admin.Hospital;
import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.entity.community.BoardFile;
import com.AniCare.demo.entity.medical.Allergy;
import com.AniCare.demo.entity.medical.ClinicDiary;
import com.AniCare.demo.entity.medical.Disease;
import com.AniCare.demo.repository.MainPage.PetRepository;
import com.AniCare.demo.repository.admin.HospitalRepository;
import com.AniCare.demo.repository.community.BoardFileRepository;
import com.AniCare.demo.repository.community.BoardRepository;
import com.AniCare.demo.repository.medical.AllergyRepository;
import com.AniCare.demo.repository.medical.ClinicDiaryRepository;
import com.AniCare.demo.repository.medical.DiseaseRepository;
import com.AniCare.demo.repository.medical.mediPetRepository;
import com.AniCare.demo.service.community.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClinicDiaryService {

    private final PetRepository petRepository;
    private final ClinicDiaryRepository clinicDiaryRepository;
    private final AllergyRepository allergyRepository;
    private final DiseaseRepository diseaseRepository;
    private final HospitalRepository hospitalRepository;
    private final BoardFileRepository boardFileRepository;
    private final BoardFileService boardFileService;
    private final BoardRepository boardRepository;
    private final mediPetRepository mediPetRepository;
    private final UserRepository userRepository;

    // --------------메서드

    // (임시) 로그인 유저 id로 반려동물 한 마리만 불러오기
    public Pet getOnePet(String userEmail) {
        return mediPetRepository.findByUserUserEmail(userEmail).stream().findFirst().orElseThrow(() -> new IllegalStateException("등록된 반려동물이 없습니다."));
    }

    // ----동물의 id로 질병/알러지 정보 불러오기
    // 질병리스트
    public List<Disease> getDiseases(Long petId) {
        return diseaseRepository.findByPet_id(petId);
    }

    // 알러지리스트
    public List<Allergy> getAllergies(Long petId) {
        return allergyRepository.findByPet_id(petId);
    }
    // ----

    // --------------메서드


    // (임시)로그인 정보로 진료수첩 출력용 clinicDiaryPetInfoDto 정보 담기
    public ClinicDiaryPetInfoDto petInfoDto(String userEmail) {
        Pet pet = getOnePet(userEmail);
        return new ClinicDiaryPetInfoDto(
                pet.getPetImage(),
                pet.getPetName(),
                pet.getPetBreed(),
                pet.getPetSex(),
                pet.getPetAge(),
                pet.getPetSpecies().getTypeName(),
                getDiseases(pet.getId()),
                getAllergies(pet.getId())
        );
    }

    // 반려동물 정보로 진료수첩 리스트 페이징으로 불러오기
    @Transactional(readOnly = true)
    public Page<ClinicDiaryListDto> findClinicDiariesByPetId(
            Long petId,
            int page,    // 0-based
            int size
    ) {
        return clinicDiaryRepository.findAllByPetId(petId, PageRequest.of(page, size));
    }

    // 진료수첩 상세페이지 dto값 전달(출력)
    @Transactional(readOnly = true)
    public ClinicDiaryDto viewClinicDiaryDetail(Long id) {
        ClinicDiary c = clinicDiaryRepository.findByClinicDiaryId(id).orElseThrow(() -> new NoSuchElementException(id + "를 찾을 수 없습니다."));

        List<Disease> diseases = diseaseRepository.findByPet_id(c.getPet().getId());
        List<Allergy> allergies = allergyRepository.findByPet_id(c.getPet().getId());
        List<BoardFile> boardFiles = boardFileRepository.findAllByBoardId(c.getBoard().getId());

        return new ClinicDiaryDto(
                c.getClinicDiaryRecordDate().toLocalDate(),
                c.getStatus(),
                diseases,
                allergies,
                c.getBoard().getBoardTitle(),
                c.getBoard().getBoardContent(),
                boardFiles,
                c.getBoard().getBoardHit(),
                c.getBoard().getLikeCount(),
                c.getHospital()
        );
    }

    // 진료수첩 저장
    @Transactional
    public void clinicDiarySave(ClinicDiarySetDto dto, Long petId, String email) {
        // 각 객체 생성
        Board b = new Board();
        ClinicDiary cd = new ClinicDiary();
        User user = userRepository.findByUserEmail(email).orElseThrow();


        // 반려동물 정보 불러오기
        Pet p = petRepository.findById(petId).orElseThrow(() -> new IllegalStateException("반려동물 정보를 로드할 수 없습니다."));

        // 보드 값 삽입 및 저장
        b.setBoardTitle(dto.getBoardTitle());
        b.setBoardContent(dto.getBoardContent());
        b.setBoardType(BoardType.CLINICDIARY);
        b.setBoardWriteDate(LocalDate.now());
        b.setUser(user);

        boardRepository.save(b);

        // 진료수첩 정보 삽입 및 저장
        cd.setPet(p);
        cd.setBoard(b);
        cd.setClinicDiaryRecordDate(dto.getClinicDiaryRecordDate().atStartOfDay());
        cd.setStatus(dto.getStatus());

        //병원 정보 저장
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId()).orElse(null);
        cd.setHospital(hospital);

        clinicDiaryRepository.save(cd);

        //이미지 업로드 -> board_file 테이블 저장
        List<MultipartFile> files = dto.getFiles();
        for (int i = 0; i < files.size(); i++) {

            //첨부파일 배열에 빈칸이 있다면 데이터 저장 X
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                continue;
            }

            BoardFile boardFile = new BoardFile();
            boardFile.setBoard(b); //board_file의 board(board_id)값 지정해주기

            //썸네일 이미지 정해주기
            if (i == 0)
                boardFile.setThumbnailYn("Y");
            else
                boardFile.setThumbnailYn("N");

            //BoardFileService 메서드 호출
            //테이블 저장 작업이 이루어지는 코드
            try {
                boardFileService.saveFile(boardFile, files.get(i));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
