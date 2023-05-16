package com.studyproject.board.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//요청 클래스
@Getter
@Setter
public class PostRequest {


    private Long id;
    private String boardWriter;
    private String boardPass; //null 가능
    private String boardTitle;
    private String boardContents;
    private String createdTime;
}
