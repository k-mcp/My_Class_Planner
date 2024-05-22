package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mcp.myclassplanner.model.dao.MemberMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private static int guestNo = 1111;

    public int getGuestNo(){
        return guestNo++;
    }

    @Autowired
    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder){
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public int signIn(Map<String,String> map) {


        String encodedPassword = memberMapper.getEncodedPassword(map.get("username"));
        if(Objects.isNull(encodedPassword)){ // if such data doesn't exist
            return 1; // username doesn't exist
        }
        boolean match = passwordEncoder.matches(map.get("password"), encodedPassword);
        if(!match) { // if password does not match
            return 2; // password incorrect
        }

        return 0; // success

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



    public int signUp(MemberDTO memberDTO){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 8; i++){
            sb.append((int)(Math.random()*10));
        }
        memberDTO.setAuthCode(sb.toString());
        memberDTO.setAuthStatus(0);
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        System.out.println("memberDTO = " + memberDTO);

        return memberMapper.signUp(memberDTO);

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



    public int checkAuthStatus(String username) {
        return memberMapper.checkAuthStatus(username);
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

    public String getEmail(String username) {
        return memberMapper.getEmail(username);
    }

    public List<MemberDTO> getAllMembers() {
        return memberMapper.getAllMembers();
    }

    public int signInAsGuest(MemberDTO member) {
        return memberMapper.signUp(member);
    }

    public int getMemberCodeByEmail(String email) {
        return memberMapper.getMemberCodeByEmail(email);
    }

    public int updatePassword(int memberCode, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        newPassword= (passwordEncoder.encode(newPassword));
        map.put("newPassword", newPassword);
        return memberMapper.updatePassword(map);
    }

    public int getLev(int memberCode) {
        return memberMapper.getLev(memberCode);
    }

    public void addExp(Map<String, Integer> map) {
        memberMapper.addExp(map);
        int memberCode = map.get("memberCode");
        int lev = memberMapper.getLev(memberCode);
        if (lev == 1 && memberMapper.getExp(memberCode) > 10){
            memberMapper.levUp(memberCode);
        }
        else if (lev == 2 && memberMapper.getExp(memberCode) > 30){
            memberMapper.levUp(memberCode);
        }
        else if (lev == 3 && memberMapper.getExp(memberCode) > 60){
            memberMapper.levUp(memberCode);
        }
        else if (lev == 4 && memberMapper.getExp(memberCode) > 100){
            memberMapper.levUp(memberCode);
        }
        else if (lev == 5 && memberMapper.getExp(memberCode) > 10000){
            memberMapper.levUp(memberCode);
        }
    }

    public int getExp(int memberCode) {
        return memberMapper.getExp(memberCode);
    }
}
