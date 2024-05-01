package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mcp.myclassplanner.model.dao.MemberMapper;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    public int signIn(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        String email = memberMapper.signIn(map);
        System.out.println("email = " + email);
        return 1;
//        if(!Objects.isNull(email)) // if username and password match
//        {
//            int auth = memberMapper.auth(email);
//            if(auth == 0) // if email is not verified
//            {
//
//            } else if(auth == 1){ // email is verified
//                // Session 방식으로 로그인 정보 저장
//            }
//        }

    }

    public void signUp(@ModelAttribute MemberDTO memberDTO){
//        int result = memberMapper.signUp(memberDTO);

    }


}
