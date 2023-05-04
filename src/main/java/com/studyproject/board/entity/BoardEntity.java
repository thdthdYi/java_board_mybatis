package com.studyproject.board.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.studyproject.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id //pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql auto_increment
    private Long id;

    //컬럼의 크기 값, not null
    @Column(length = 20)
    private String boardWriter;

    @Column
    private String boardPass; //null 가능

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int Hits;

    //변환 메소드 작업
    //save.html에서 입력한 값을 BoardDTO에 담은 후에 작성자 값을 Entity의 값으로 set
    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle((boardDTO.getBoardTitle()));
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setHits(0);
        return boardEntity;
    }
}
