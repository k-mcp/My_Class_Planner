package mcp.myclassplanner.model.dto;

public class MemberDTO {
    private int memberNo; // pk
    private String email; //
    private String username;
    private String password;
    private String authCode; // auto generated
    private int authorized; // 0 = not authorized, 1=authorized

}
