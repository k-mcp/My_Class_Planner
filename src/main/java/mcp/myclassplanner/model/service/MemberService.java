package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mcp.myclassplanner.model.dao.MemberMapper;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Base64;
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

        String pass1 = passwordEncoder.encode(password);
        System.out.println("pass1 = " + pass1);
        int result = memberMapper.signIn(username,pass1);
        System.out.println("resul123213t = " + result);
        
        // 맵을 사용하여 MemberMapper의 signIn 메서드를 호출하여 이메일을 가져오고,
//        String email = memberMapper.signIn(map);

        // 이메일을 출력하여 확인
//        System.out.println("email = " + email);

//        // 조회된 이메일이 없으면 로그인 실패
//        if (Objects.isNull(email)) {
//            return 0; // 조회된 이메일이 없을 경우
//        }

        return result;

    }

    public int signInWithAuth(String username, String password) {

        // MemberMapper를 통해 username과 password로 이메일을 조회
        String email = memberMapper.signInWithAuth(username, password);

        // 조회된 이메일이 없으면 로그인 실패
        if (Objects.isNull(email)) {
            return 0; // 조회된 이메일이 없을 경우
        }

        // 회원의 authCode 조회
        int authCode = memberMapper.getAuthCodeByEmail(email);

        // authCode가 null이거나 1이 아니면 로그인 실패 (이메일 인증이 안된 상태)
        if (authCode != 1) {
            return 2; // authcode 가 null이거나 1이 아닌 경우
        }

        // 모든 조건을 통과했을 때, 로그인 성공을 의미하는 값 반환
        return 1;
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


    public int check(String username) {
        Integer check = memberMapper.check(username);
        System.out.println("check123 = " + check);
        return check;
    }
}
