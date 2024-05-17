package mcp.myclassplanner.controller;

import mcp.myclassplanner.model.dto.BoardDTO;
import mcp.myclassplanner.model.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String board(Model model, @RequestParam(defaultValue = "0") int page){
        List<BoardDTO> boardDTOList = boardService.findAll(page);
        model.addAttribute("boardPage", boardDTOList);

        return "board/board";
    }
}
