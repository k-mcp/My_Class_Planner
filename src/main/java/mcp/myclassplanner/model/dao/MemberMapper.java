package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    int signUp(MemberDTO memberDTO);

    MemberDTO signUpErrorByEmail(MemberDTO memberDTO);

    MemberDTO signUpErrorByUsername(MemberDTO memberDTO);

    int getAuthCodeByEmail(String email);

    String signInWithAuth(String username, String password);

    String getAuthCode(String email);

    MemberDTO authorize(Map<String, String> map);

    void authStatus(Map<String, String> map);

    String getEncodedPassword(String username);

    int checkAuthStatus(String username);

    String getEmail(String username);

    List<MemberDTO> getAllMembers();

    int getMemberCodeByEmail(String email);

    int updatePassword(Map<String, Object> map);

    int getLev(int memberCode);
}
