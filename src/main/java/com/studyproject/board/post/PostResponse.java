package com.studyproject.board.post;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class PostResponse {

    @Id //pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
