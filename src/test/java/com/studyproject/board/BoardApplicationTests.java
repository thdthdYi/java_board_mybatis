package com.studyproject.board;

import com.studyproject.board.post.PostMapper;
import com.studyproject.board.post.PostRequest;
import com.studyproject.board.post.PostResponse;
import com.studyproject.board.post.PostService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	PostMapper postMapper;
	@Autowired
	PostService postService;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Test
	public void testByApplicationContext() {
		try {
			System.out.println("=========================");
			System.out.println(context.getBean("abc"));
			System.out.println("=========================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBySqlSessionFactory() {
		try {
			System.out.println("=========================");
			System.out.println(sessionFactory.toString());
			System.out.println("=========================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void save() {
		PostRequest params = new PostRequest();
		params.setBoardTitle("1번 게시글 제목");
		params.setBoardContents("1번 게시글 내용");
		params.setBoardWriter("테스터");
		postMapper.save(params);

		Long id = postService.savePost(params);
		System.out.println("전체 게시글 개수는 : " + id+ "개입니다.");
	}

	/*
	@Test
	void save() {
		PostRequest params = new PostRequest();
		params.setBoardTitle("1번 게시글 제목");
		params.setBoardContents("1번 게시글 내용");
		params.setBoardWriter("테스터");
//      params.setNoticeYn(false);
		postMapper.save(params);
		List<PostResponse> posts = postMapper.findAll();


		System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
	}*/

}