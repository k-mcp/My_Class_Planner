package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {

    String signIn(Map<String, String> map);

    void signUp(MemberDTO memberDTO);

    MemberDTO signUpErrorByEmail(MemberDTO memberDTO);

    MemberDTO signUpErrorByUsername(MemberDTO memberDTO);
}
