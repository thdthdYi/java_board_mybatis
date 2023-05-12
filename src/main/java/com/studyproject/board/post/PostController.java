package com.studyproject.board.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성 페이지
    //@RequestParam > html에서 보낸 파라미터를 전달 받음
    @GetMapping("/board/save")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if (id != null) {
            PostResponse post = postService.findPostById(id);
            model.addAttribute("boardList", post);
        }
        return "save";
    }

    @PostMapping("/board")
    public String savePost(final PostRequest params) {
        postService.savePost(params);
        return "redirect:board/list";
    }

    // 게시글 리스트 페이지
    @GetMapping("/board/list")
    public String openPostList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("boardList", posts);
        return "list";
    }

    //게시글 클릭 시 상세 내용 조회 페이지로 이동
    @GetMapping("/board/{id}")
    public String openPostView(@PathVariable final Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("board", post);
        return "detail";
    }

    // 기존 게시글 수정 후 이동 페이지
    @PostMapping("/board/update")
    public String updatePost(final PostRequest params) {
        postService.updatePost(params);

        return "redirect:/board/list";
    }

    //기존 게시글 정보를 조회하여 update.html에 던져줌
    @GetMapping("/board/update")
    public String openPostUpdate(@RequestParam("id") Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("boardUpdate", post);
        return "update";
    }

    @GetMapping("/board/delete")
    public String deletePost(@RequestParam final Long id) {
        postService.deletePost(id);
        return "redirect:/board/list";
    }


}