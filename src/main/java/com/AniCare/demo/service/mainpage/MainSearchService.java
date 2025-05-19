package com.AniCare.demo.service.mainpage;

import com.AniCare.demo.Dto.mainpage.SearchResultDto;
import com.AniCare.demo.constant.community.BoardType;
import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.repository.community.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MainSearchService {

    private final BoardRepository boardRepository;


    public List<SearchResultDto> searchAllByKeyword(String keyword) {
        List<SearchResultDto> results = new ArrayList<>();

        List<Board> boards = boardRepository.searchByKeywordAndType(List.of(BoardType.ERRAND, BoardType.MEETING, BoardType.CLINICDIARY), keyword);

        for(Board board : boards){
            LocalDate createAt = board.getBoardWriteDate() != null
                    ? board.getBoardWriteDate() : LocalDate.now();

            SearchResultDto resultDto = new SearchResultDto(
                    board.getBoardTitle(),
                    "/board/boardDetail/" + board.getId(),
                    board.getBoardType().name(),
                    board.getBoardHit(),
                    createAt
            );

            results.add(resultDto);
        }
        return results;

    }

}



