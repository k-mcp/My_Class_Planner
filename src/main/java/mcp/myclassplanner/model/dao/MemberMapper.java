package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.MemberDTO;
import mcp.myclassplanner.model.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    void addExp(Map<String, Integer> map);

    int getExp(int memberCode);

    void levUp(int memberCode);

    // 검색 메서드 추가
    List<MemberDTO> searchUsers(@Param("query") String query);

    Integer getMemberCodeByUsername(String username);

    List<String> searchAllMember(String query);

    Optional<User> findByUsername(String username);
}
