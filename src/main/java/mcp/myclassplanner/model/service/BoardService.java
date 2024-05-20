package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dao.BoardMapper;
import mcp.myclassplanner.model.dto.BoardDTO;
import mcp.myclassplanner.model.dto.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private final BoardMapper boardMapper;
    public BoardService(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }

    public List<BoardDTO> findAll(int page) {
        return boardMapper.findAll(page);
    }

    public BoardDTO viewByBoardNo(int boardNo) {
        return boardMapper.viewByBoardNo(boardNo);
    }

    public List<CommentDTO> viewCommentByBoardNo(int boardNo) {
        return boardMapper.viewCommentByBoardNo(boardNo);
    }

    public Integer totalRecord() {
        return boardMapper.totalRecord();
    }

    public void postPro(Map<String, Object> map) {
        boardMapper.postPro(map);
    }

    public int comment(Map map) {

        return boardMapper.comment(map);
    }
}
