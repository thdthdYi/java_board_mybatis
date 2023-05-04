package com.studyproject.board.dto;

import com.studyproject.board.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

//DTO(Data Transfer Object) 데이터를 전송할 때 쓰이는 객체
//lombok 라이브러리 메소드로 get, set
//save.html 파일의 body의 필드값이 동일하다면, 해당하는 필드의 값들을 담아줌
@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardDTO.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardDTO.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;
    }

}
