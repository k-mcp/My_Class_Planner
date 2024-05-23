package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.service.MemberService;
import mcp.myclassplanner.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchUserController {

    @Autowired
    private MemberService memberService; // MemberService를 Autowired하여 인스턴스 생성

    //@RequestParam("query")
    @GetMapping("/searchUsers")
    public String searchUsers(String query, Model model, HttpSession session) {
        // MemberService를 통해 검색된 사용자 목록을 가져옴
        List<MemberDTO> searchResults = memberService.searchUsers(query);
        String username = (String) session.getAttribute("username");

        model.addAttribute("searchResults", searchResults); // 검색 결과를 모델에 추가
        model.addAttribute("username", username);
        return "search/searchUsers";
    }

}
