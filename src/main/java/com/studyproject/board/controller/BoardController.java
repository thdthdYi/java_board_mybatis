package com.studyproject.board.controller;

import com.studyproject.board.dto.BoardDTO;
import com.studyproject.board.entity.BoardEntity;
import com.studyproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") //"/board"가 계속 반복되기 때문에
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    //save.html의 body의 내용을 수행하기 위함
    //GetMapping이 2개일 경우 Ambiguous mapping error 발생, 같은메소드와 같은주소를 쓸 수는 없기때문에 postmapping으로 사용
    @PostMapping("/save")
        public String save(@ModelAttribute BoardDTO boardDTO){
            System.out.println("boardDTO =" +boardDTO);
            boardService.save(boardDTO);
            return "index";

    }

    @GetMapping("/")
    public String findAll(Model model){
        //db에서 전체 게시글 데이터를 가져온 후 list.html에 보여줌
        List<BoardDTO> boardDTOList = boardService.findAll();
        //가져온 데이터를 model에 담기
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable){

       // boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model){
        BoardDTO board = boardService.update(boardDTO);

        //수정 후에 보여지는 화면
        model.addAttribute("board", board);
        return "detail";
        //return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }

    //페이지
    @GetMapping("/paging/")
    public String paging(@PageableDefault(page = 1)Pageable pageable, Model model){
        //pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";

    }

}
