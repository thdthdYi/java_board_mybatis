package com.studyproject.board.post;

import lombok.Getter;

import java.time.LocalDateTime;

//데이터 처리 응답 클래스

@Getter
public class PostResponse {

    private Long id;
    private String boardWriter;
    private String boardPass; //null 가능
    private String boardTitle;
    private String boardContents;
    private String createdTime;
}
