package com.studyproject.board.post;

import com.studyproject.board.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//PostMapper.xml(db에 접근해서 호출할 sql쿼리를 선언)와 함께 db와 통신출
//PostMapper.xml의 id와 메서드명이 동일해야함.

@Mapper
public interface PostMapper {

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    //INSERT 쿼리 호출 부분 params에 저장할 게시글이 담김
    void save(PostRequest params);

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    PostResponse findById(Long id);

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(PostRequest params);

    /**
     * 게시글 삭제
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<PostResponse> findAll();

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */

    int count(SearchDTO params);

}
