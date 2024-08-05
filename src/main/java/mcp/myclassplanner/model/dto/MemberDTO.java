package mcp.myclassplanner.model.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private int memberCode; // pk
    private String email; //
    private String username;
    private String password;
    private String authCode; // auto generated
    private int authStatus; // 0 = not authorized, 1=authorized
    private int lev;
    private int ex;


}
