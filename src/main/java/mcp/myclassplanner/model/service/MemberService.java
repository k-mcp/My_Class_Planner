package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mcp.myclassplanner.model.dao.MemberMapper;

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

        String encodedPassword = memberMapper.signIn(username);
        boolean match = passwordEncoder.matches(password, encodedPassword);
        System.out.println("match = " + match);
        return 1;

    }

    public void signUp(MemberDTO memberDTO){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 8; i++){
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

    public String getAuthCode(String email) {
        return memberMapper.getAuthCode(email);
    }

    public int authorize(Map<String, String> map) {
        System.out.println("map.get(\"email\") = " + map.get("email"));
        System.out.println("map.get(\"authKey\") = " + map.get("authKey"));
        MemberDTO member = memberMapper.authorize(map);
        if(!Objects.isNull(member)){
            memberMapper.authStatus(map);
            return 1; // succeed
        } else {
            return 0; // fail
        }
    }
}
