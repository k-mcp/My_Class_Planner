package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> findAll(int page);
}
