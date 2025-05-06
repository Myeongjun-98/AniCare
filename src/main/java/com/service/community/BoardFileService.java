package com.service.community;

import com.entity.community.BoardFile;
import com.repository.community.BoardFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class BoardFileService {
    @Value("${filePath}")
    private String uploadPath;

    private final BoardFileRepository boardFileRepository;

    // ================ 커뮤니티 - 게시글 첨부파일 이미지 업로드 ================
    public void saveFile(BoardFile boardFile,
                         MultipartFile multipartFile) throws Exception {

        String originalName = multipartFile.getOriginalFilename();
        String savedName = "";
        String fileUrl = "";

        //이미지가 저장되어있다면:
        if(!StringUtils.isEmpty(originalName)){
            savedName = uploadFile(originalName, multipartFile.getBytes());
            fileUrl = "/anicareFile/"+savedName;
        }

        //entity에 저장
        boardFile.setFileOriginalname(originalName);
        boardFile.setFileUrl(fileUrl);
        boardFile.setFileSavedname(savedName);

        //테이블에 저장
        boardFileRepository.save(boardFile);

    }

    // ================ 커뮤니티 - 게시글 첨부파일 이미지 업로드 ================
    public String uploadFile(String originalFileName,
                             byte[] fileData) throws Exception{

        //fileSavedName에 사용할 랜덤 문자열 생성
        UUID uuid = UUID.randomUUID();
        //확장자 추출 - .png, .jpg, .jpeg, .gif 등등...
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        //랜덤 문자열(uuid.toString()) + 업로드된 이미지의 확장자 로 새 파일 이름 생성
        String savedName = uuid + ext;

        //boardFileUrl = 저장 경로 + 새 파일 이름 : 첨부파일 저장경로 지정
        String fileUrl = uploadPath + "/" + savedName;

        //FileOutputStream: 바이트(byte) 단위로 파일 쓰는 클래스
        //지정된 경로에 파일이 없으면 새로 생성. 있으면 덮어쓰기
        FileOutputStream fos = new FileOutputStream(fileUrl);

        //fileData: 업로드 파일의 실제 내용 -> write 메서드를 통해 디스크에 바이트 단위로 저장.
        //실제로 파일을 저장시켜주는 작업
        fos.write(fileData);

        //파일을 저장하고 스트림(FileOutputStream) 닫아주는 작업.
        //닫지 않을 시 저장이 안끝나는걸로 처리되어 후 작업에 문제가 생길 수 있습니다.
        fos.close();

        return savedName;
    }




}
