package mcp.myclassplanner.model.dto;

import java.sql.Timestamp;

public class CommentDTO {
    private int commentNo;
    private String username;
    private Timestamp commentTime;
    private String commentContent;
    private int boardNo;
    private int rank;

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentNo=" + commentNo +
                ", username='" + username + '\'' +
                ", commentTime=" + commentTime +
                ", commentContent='" + commentContent + '\'' +
                ", boardNo=" + boardNo +
                ", rank=" + rank +
                '}';
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public CommentDTO(int commentNo, String username, Timestamp commentTime, String commentContent, int boardNo, int rank) {
        this.commentNo = commentNo;
        this.username = username;
        this.commentTime = commentTime;
        this.commentContent = commentContent;
        this.boardNo = boardNo;
        this.rank = rank;
    }

    public CommentDTO() {
    }
}