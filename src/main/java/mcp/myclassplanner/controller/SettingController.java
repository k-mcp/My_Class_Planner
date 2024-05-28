

package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.MemberDTO;
import mcp.myclassplanner.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;
import java.io.IOException;

@Controller
@PropertySource("classpath:application.properties")
public class SettingController {

    @Value("${API_KEY}")
    private String API_KEY;

    private final MemberService memberService;

    @Autowired
    public SettingController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/settings")
    public ModelAndView settings(HttpSession session, ModelAndView mv) {
        // 사용자 이름을 세션에서 가져와 모델에 추가
        String username = (String) session.getAttribute("username");
        mv.addObject("username", username);
        int lev = memberService.getLev((int)session.getAttribute("memberCode"));
        mv.addObject("rank", lev);
        int exp = memberService.getExp((int)session.getAttribute("memberCode"));
        mv.addObject("nextRank",exp);
        mv.setViewName("settings/settings");
        mv.addObject("API_KEY", API_KEY);
        return mv;
    }


    @PostMapping("/updatePassword")
    public ModelAndView updatePassword(ModelAndView mv,
                                       @RequestParam("newPassword") String newPassword,
                                       @RequestParam("confirmPassword") String confirmPassword,
                                       HttpSession session, RedirectAttributes redirectAttributes) {

        int memberCode = (int) session.getAttribute("memberCode");

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("message", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            mv.setViewName("redirect:/settings");
            return mv;
        }
        int affectedRows = memberService.updatePassword(memberCode, newPassword);
        if (affectedRows > 0) {
            redirectAttributes.addFlashAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "비밀번호 변경에 실패했습니다.");
        }
        mv.setViewName("redirect:/settings");
        return mv;
    }

    @PostMapping("/resetPassword")
    public ModelAndView resetPassword(ModelAndView mv,
                                      @RequestParam("newPassword") String newPassword,
                                      @RequestParam("confirmPassword") String confirmPassword,
                                      HttpSession session, RedirectAttributes redirectAttributes) {

        int memberCode = memberService.getMemberCodeByEmail((String) session.getAttribute("email"));
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("message", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            mv.setViewName("/");
            return mv;
        }
        int affectedRows = memberService.updatePassword(memberCode, newPassword);
        if (affectedRows > 0) {
            redirectAttributes.addFlashAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "비밀번호 변경에 실패했습니다.");
        }
        mv.setViewName("redirect:/auth/signin");
        return mv;
    }


}