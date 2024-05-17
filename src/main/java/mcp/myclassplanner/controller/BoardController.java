package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.BoardDTO;
import mcp.myclassplanner.model.dto.CommentDTO;
import mcp.myclassplanner.model.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.stream.events.Comment;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

@Controller
public class BoardController {

    private final BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String board(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session){
        Integer totalRecord = boardService.totalRecord();
        int totalPage = 1;
        if(!Objects.isNull(totalRecord)){
            totalPage = totalRecord / 10;
            if(totalRecord % 10 != 0) totalRecord++;
        }
        String username = (String)session.getAttribute("username");
        int memberCode = (int)session.getAttribute("memberCode");
        List<BoardDTO> boardDTOList = boardService.findAll(page);
        model.addAttribute("boardPage", boardDTOList);
        model.addAttribute("username", username);
        model.addAttribute("memberCode", memberCode);
        model.addAttribute("totalPage", totalPage);

        return "board/board";
    }

    @GetMapping("/board/view")
    public String viewBoard(int boardNo, Model model, HttpSession session){
        BoardDTO boardDTO = boardService.viewByBoardNo(boardNo);
        List<CommentDTO> comment = boardService.viewCommentByBoardNo(boardNo);
        model.addAttribute("boardDTO",boardDTO);
        model.addAttribute("commentDTOs",comment);
        String username = (String)session.getAttribute("username");
        int memberCode = (int)session.getAttribute("memberCode");
        model.addAttribute("username", username);
        model.addAttribute("memberCode", memberCode);
        model.addAttribute("boardNo", boardNo);
        return "board/view";
    }
}
