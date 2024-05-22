package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.BoardDTO;
import mcp.myclassplanner.model.dto.CommentDTO;
import mcp.myclassplanner.model.service.BoardService;
import mcp.myclassplanner.model.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    public BoardController(BoardService boardService, MemberService memberService){
        this.boardService = boardService;
        this.memberService = memberService;
    }

    @GetMapping("board")
    public String board(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session){
        Integer totalRecord = boardService.totalRecord();
        int totalPage = 1;
        if(boardService.totalRecord() != 0){
            totalPage = totalRecord / 10;
            if(totalRecord % 10 != 0) totalPage++;
        }
        String username = (String)session.getAttribute("username");
        int memberCode = (int)session.getAttribute("memberCode");
        List<BoardDTO> boardDTOList = boardService.findAll(page*10);
        model.addAttribute("boardPage", boardDTOList);
        model.addAttribute("username", username);
        model.addAttribute("memberCode", memberCode);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("boardNo", page);
        model.addAttribute("page",page);
        model.addAttribute("lev", memberService.getLev(memberCode));

        return "board/board";
    }
    @GetMapping("board/deletePost")
    public String deletePost(HttpSession session){
        int boardNo = (int) session.getAttribute("boardNo");
        boardService.deletePost(boardNo);
        return "redirect:/board";
    }

    @GetMapping("board/view")
    public String viewBoard(int boardNo, Model model, HttpSession session){
        BoardDTO boardDTO = boardService.viewByBoardNo(boardNo);
        List<CommentDTO> comment = boardService.viewCommentByBoardNo(boardNo);
        session.setAttribute("boardNo", boardNo);
        model.addAttribute("boardDTO",boardDTO);
        model.addAttribute("commentDTOs",comment);
        String username = (String)session.getAttribute("username");
        int memberCode = (int)session.getAttribute("memberCode");
        model.addAttribute("username", username);
        model.addAttribute("memberCode", memberCode);
        model.addAttribute("boardNo", boardNo);
        boardService.getAuthor(boardNo);
        if(boardService.getAuthor(boardNo).equals(username)){
            model.addAttribute("author", true);
        }
        return "board/view";
    }
    @PostMapping("board/view")
    public String comment(RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String username =(String)session.getAttribute("username");
        int boardNo = (int)session.getAttribute("boardNo");
        map.put("boardNo", boardNo);
        map.put("username", username);
        map.put("commentTime", new Timestamp(new Date().getTime()));
        Map<String, String[]> parameters = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String comment = Arrays.toString(entry.getValue()).replace("[", "").replace("]", "");
            map.put("comment", comment);
        }
        int result = boardService.comment(map);
        return "redirect:/board/view?boardNo="+boardNo;
    }

    @GetMapping("board/post")
    public String postBoard(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "board/post";
    }
    @PostMapping("board/post")
    public String postBoardPro(HttpSession session, Model model, HttpServletRequest request){
        String username = (String) session.getAttribute("username");
        int memberCode = (int) session.getAttribute("memberCode");
        model.addAttribute("memberCode", memberCode);
        model.addAttribute("username", username);
        int index = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("time", new Timestamp(new Date().getTime()));
        Map<String, String[]> parameters = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String i = Arrays.toString(entry.getValue()).replace("[", "").replace("]", "");
            map.put("index"+index++,i);
        }
        boardService.postPro(map);

        return "redirect:/board";
    }
    @GetMapping("/searchBy")
    public String searchPostByTitle(String searchType, String searchValue, Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        model.addAttribute("notShow", true);
        List<BoardDTO> boardDTOList;
        switch (searchType) {
            case "Title":
                boardDTOList = boardService.findByTitle(searchValue);
                model.addAttribute("boardPage", boardDTOList);
                model.addAttribute("username", username);
                return "board/board";
            case "Context":
                boardDTOList = boardService.findByContext(searchValue);
                model.addAttribute("boardPage", boardDTOList);
                model.addAttribute("username", username);
                return "board/board";
            case "Author":
                boardDTOList = boardService.findByAuthor(searchValue);
                model.addAttribute("boardPage", boardDTOList);
                model.addAttribute("username", username);
                return "board/board";
        }
        return "redirect:/board";
    }
}
