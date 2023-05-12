package com.studyproject.board.post;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
