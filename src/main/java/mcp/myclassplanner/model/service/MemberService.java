package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mcp.myclassplanner.model.dao.MemberMapper;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder){
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
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

    public void signUp(MemberDTO memberDTO){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 14; i++){
            sb.append((int)(Math.random()*10));
        }
        memberDTO.setAuthCode(sb.toString());
        memberDTO.setAuthStatus(0);
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        System.out.println("memberDTO = " + memberDTO.toString());

        memberMapper.signUp(memberDTO);

    }


    public int signUpError(MemberDTO memberDTO) {
        MemberDTO member = memberMapper.signUpErrorByUsername(memberDTO);
        if(!Objects.isNull(member)){
            return 1; // username already exist
        }
        member = memberMapper.signUpErrorByEmail(memberDTO);
        if(!Objects.isNull(member)){
            return 2; // email already exist
        }
        return 0;
    }
}
