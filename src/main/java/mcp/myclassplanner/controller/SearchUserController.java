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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class SearchUserController {

    private final MemberService memberService; // MemberService를 Autowired하여 인스턴스 생성
    private final PlanService planService;

    @Value("${API_KEY}")
    private String API_KEY;

    @Autowired
    public SearchUserController(MemberService memberService, PlanService planService) {
        this.memberService = memberService;
        this.planService = planService;
    }


    //@RequestParam("query")
    @GetMapping("/searchUsers")
    public String searchUsers(String query, Model model, HttpSession session) {
        // MemberService를 통해 검색된 사용자 목록을 가져옴
        List<MemberDTO> searchResults = memberService.searchUsers(query);
        String username = (String) session.getAttribute("username");

        model.addAttribute("searchResults", searchResults); // 검색 결과를 모델에 추가
        model.addAttribute("username", username);

        model.addAttribute("API_KEY", API_KEY);
        return "search/searchUsers";
    }

    @PostMapping("/searchUsers")
    public String searchedUser(HttpServletRequest request, Model model, HttpSession session){
        String username = request.getParameter("searchInput");
        Integer memberCode = memberService.getMemberCodeByUsername(username);
        if(Objects.isNull(memberCode)){
            model.addAttribute("message","There is no such username.");
            return "search/searchUsers";
        }

        List<PlanDTO> planDTOS = planService.viewMyPlan(memberCode);
        // 유저가 플랜 저장을 안한경우
        if(planDTOS.size() == 0){
            model.addAttribute("message","User has not saved any plans yet.");
            return "search/searchUsers";
        }
        for(PlanDTO planDTO : planDTOS){
            if (planDTO.getDays().contains("X")){
                planDTO.setDays(planDTO.getDays().replace("X","Th"));
            }
        }
        Map<Integer, List<PlanDTO>> groupedPlans = planDTOS.stream()
                .collect(Collectors.groupingBy(PlanDTO::getCaseNo));
        model.addAttribute("groupedPlans", groupedPlans);
        model.addAttribute("username", (String)session.getAttribute("username"));

        model.addAttribute("API_KEY", API_KEY);
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
