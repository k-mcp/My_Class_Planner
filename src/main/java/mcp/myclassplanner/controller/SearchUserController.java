package mcp.myclassplanner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.PlanDTO;
import mcp.myclassplanner.model.service.MemberService;
import mcp.myclassplanner.model.dto.MemberDTO;
import mcp.myclassplanner.model.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SearchUserController {

    @Autowired
    private MemberService memberService; // MemberService를 Autowired하여 인스턴스 생성

    @Autowired
    private PlanService planService;

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

    @PostMapping("/searchUsers")
    public String searchedUser(HttpServletRequest request, Model model){
        String username = request.getParameter("query");
        int memberCode = memberService.getMemberCodeByUsername(username);
        List<PlanDTO> planDTOS = planService.viewMyPlan(memberCode);
        for(PlanDTO planDTO : planDTOS){
            if (planDTO.getDays().contains("X")){
                planDTO.setDays(planDTO.getDays().replace("X","Th"));
            }
        }
        Map<Integer, List<PlanDTO>> groupedPlans = planDTOS.stream()
                .collect(Collectors.groupingBy(PlanDTO::getCaseNo));
        model.addAttribute("groupedPlans", groupedPlans);
        return "search/searchUsers";
    }

    @PostMapping(value="search", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String memberExist(@RequestBody Map<String,Object> query) throws JsonProcessingException {
        String username = (String) query.get("query");
        List<String> usernames = memberService.searchAllMember(username);
        Map<String,String> map = new HashMap<>();
        for(String name : usernames){
            map.put(name,name);
        }

        ObjectMapper mapper = new ObjectMapper();
        Object a = mapper.writeValueAsString(map);
        return mapper.writeValueAsString(usernames);
    }

}
