package com.studyproject.board.paging;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination; //계산된 페이지 정보 저장

    public PagingResponse(List<T> list, Pagination pagination) {
        this.list.addAll(list);
        this.pagination = pagination;
    }

}
