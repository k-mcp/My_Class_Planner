package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.MemberDTO;
import mcp.myclassplanner.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;
import java.io.IOException;

@Controller
public class SettingController {

    //비밀번호 변경
    @Autowired
    private MemberService memberService;

    @GetMapping("/settings")
    public String settings(HttpSession session, Model model) {
        // 사용자 이름을 세션에서 가져와 모델에 추가
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "settings/settings";
    }


    @PostMapping("/updatePassword")
    public String updatePassword(
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session, RedirectAttributes redirectAttributes) {

        String username = (String) session.getAttribute("username");
        MemberDTO member = (MemberDTO) session.getAttribute("username");

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("message", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            return "redirect:/settings";
        }
        if (member == null) {
            throw new IllegalArgumentException("Member 정보가 없습니다.");
        }

//        int affectedRows = memberService.updatePassword(member.getUsername(), newPassword);
//        if (affectedRows>0) {
//            redirectAttributes.addFlashAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
//        } else {
//            redirectAttributes.addFlashAttribute("message", "비밀번호 변경에 실패했습니다.");
//        }
//
        return "redirect:/settings";
    }
}





//    @PostMapping("/settings/upload")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//        if (file.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "파일을 선택해주세요.");
//            return "redirect:/settings";
//        }
//        // 저장할 디렉토리 지정
//        String uploadDir = "/home/username/uploads/";
//
//        // 디렉토리가 존재하지 않는 경우 생성
//        File directory = new File(uploadDir);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        // 파일 저장 로직 구현
//        try {
//            // 파일을 서버나 지정된 위치에 저장
//            file.transferTo(new File("/path/to/your/uploads/directory" + file.getOriginalFilename()));
//            redirectAttributes.addFlashAttribute("message", "성공적으로 업로드되었습니다: " + file.getOriginalFilename());
//        } catch (IOException e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("message", "업로드에 실패했습니다: " + file.getOriginalFilename());
//        }
//
//        return "redirect:/settings";
//    }
//}