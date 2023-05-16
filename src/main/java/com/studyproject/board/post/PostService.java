package com.studyproject.board.post;


import com.studyproject.board.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

//비즈니스 로직을 담당
//Controller가 Request를 받으면 적절한 Service에 전달, 비즈니스 로직 처리
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    @Transactional
    public Long savePost(final PostRequest params) {
        postMapper.save(params);
        return params.getId();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     * */

    public PostResponse findPostById(final Long id) {
        return postMapper.findById(id);
    }


    public List<PostResponse> findAllPost() {
    return postMapper.findAll();
    }


    //페이지네이션
    //SearchDTO 파라미터를 전달받기 위한 로직\

    //SearchDTO 파라미터 전달받기
    public List<PostResponse> findPagePost(final SearchDTO params) {
        return postMapper.findPageAll(params);
    }

    public int getCount(final SearchDTO params){
        return postMapper.count(params);
    }

    @Transactional
    public Long updatePost(final PostRequest params) {
        postMapper.update(params);
        return params.getId();
    }

    public Long deletePost(final Long id) {
        postMapper.deleteById(id);
        return id;
    }




}
