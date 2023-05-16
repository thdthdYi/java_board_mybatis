package com.studyproject.board.post;

import com.studyproject.board.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // 페이지네이션
    @GetMapping("/board/paging")    //paging을 위한 url 호출
    public String openPageList(
            @RequestParam(value = "page", required = false) String page,
            @ModelAttribute("params") final SearchDTO params, Model model) {

        if (page != null) {
            params.setPage(Integer.parseInt(page));
        }

        //@ModelAttribute 를 활용하여 해당 파라미터로 수집한 정보를 html로 전달
        List<PostResponse> posts = postService.findPagePost(params);

        int getPageCount = postService.getCount(params);
        int totalPageCount = (getPageCount - 1) / params.getRecordSize() + 1;
        int currentPage = params.getPage();
        int blockLimit = 5;

        int startPage = ((currentPage - 1) / blockLimit) * blockLimit + 1;
        int endPage = Math.min(startPage + blockLimit - 1, totalPageCount);

        // 한 페이지에 표시할 게시글 수 (예시로 5개로 설정)
        int itemsPerPage = 5;

        // 현재 페이지에 해당하는 게시글의 시작 인덱스와 끝 인덱스 계산
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, posts.size());

        // 한 페이지에 표시할 게시글 목록 추출
        List<PostResponse> responses = posts.subList(startIndex, endIndex);

        //service에서 로직 처리를 위한 파라미터 전달
        model.addAttribute("boardList", posts);
        model.addAttribute("responses", responses);

        model.addAttribute("getPageCount", getPageCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);//html에 데이터 전달
        return "paging"; //전달받을 html
    }

}