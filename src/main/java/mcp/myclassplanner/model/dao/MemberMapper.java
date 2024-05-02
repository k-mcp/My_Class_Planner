package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {


    

    String signIn(String username);


    void signUp(MemberDTO memberDTO);

    MemberDTO signUpErrorByEmail(MemberDTO memberDTO);

    MemberDTO signUpErrorByUsername(MemberDTO memberDTO);


    int getAuthCodeByEmail(String email);

    String signInWithAuth(String username, String password);
    

    Integer check(String username);

    int signIn(String username, String pass1);

    void sendAuthCode(MemberDTO memberDTO);

    String getAuthCode(String email);

    MemberDTO authorize(Map<String, String> map);

    void authStatus(Map<String, String> map);

}
