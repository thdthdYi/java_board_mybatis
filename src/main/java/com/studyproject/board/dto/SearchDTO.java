package com.studyproject.board.dto;

import lombok.Getter;
import lombok.Setter;

//페이징을 하기 위해 수집 및 전달할 파라미터

@Getter
@Setter
public class SearchDTO {

    private int page;             // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;          // 화면 하단에 출력할 페이지 사이즈// 검색 유형
    private int pageLimit;         // 제한 페이지 수

    public SearchDTO() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
        this.pageLimit = 5;
    }
}