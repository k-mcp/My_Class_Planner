package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.BoardDTO;
import mcp.myclassplanner.model.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> findAll(int page);

    BoardDTO viewByBoardNo(int boardNo);

    List<CommentDTO> viewCommentByBoardNo(int boardNo);

    Integer totalRecord();
}
