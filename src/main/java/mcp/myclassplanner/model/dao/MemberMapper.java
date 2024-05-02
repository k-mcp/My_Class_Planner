package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    

    void signUp(MemberDTO memberDTO);

    MemberDTO signUpErrorByEmail(MemberDTO memberDTO);

    MemberDTO signUpErrorByUsername(MemberDTO memberDTO);

    int getAuthCodeByEmail(String email);

    String signInWithAuth(String username, String password);
    

    Integer check(String username);

    int signIn(String username, String pass1);
}
