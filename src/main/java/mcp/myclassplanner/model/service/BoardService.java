package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dao.BoardMapper;
import mcp.myclassplanner.model.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper boardMapper;
    public BoardService(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }

    public List<BoardDTO> findAll(int page) {
        return boardMapper.findAll(page);
    }
}
