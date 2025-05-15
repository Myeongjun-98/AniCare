package com.AniCare.demo.Dto.mainpage;

import com.AniCare.demo.entity.community.Board;
import com.AniCare.demo.entity.community.BoardFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityPopuarListDto {
    private String boardTitle;
    private String boardContent;
    private String boardImg;
    private Long boardId;

    public static CommunityPopuarListDto of (Board board, BoardFile boardFile){
        CommunityPopuarListDto communityPopuarListDto = new CommunityPopuarListDto();
        communityPopuarListDto.setBoardContent(board.getBoardContent());
        communityPopuarListDto.setBoardId(board.getId());
        communityPopuarListDto.setBoardTitle(board.getBoardTitle());
        communityPopuarListDto.setBoardImg(boardFile.getFileUrl());

        return communityPopuarListDto;
    }
}
