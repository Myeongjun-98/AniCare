package repository.community;

import entity.community.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Long> { //boardFile 테이블 데이터를 다루는 Repo

    //해당 게시글의 썸네일 이미지 가져오기
    BoardFile findByFileIdAndThumbnailYn(Long boardId, String y);

    //해당 게시글의 이미지 전부 불러오기
    List<BoardFile> findByFileId(Long fileId);

    //게시글 이미지 삭제
    void deleteByBoardId(Long boardId);
}
