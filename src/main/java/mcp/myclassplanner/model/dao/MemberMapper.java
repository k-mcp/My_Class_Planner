package mcp.myclassplanner.model.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {

    String signIn(Map<String, String> map);

}
