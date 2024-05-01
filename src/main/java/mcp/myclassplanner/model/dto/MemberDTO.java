package mcp.myclassplanner.model.dto;

public class MemberDTO {
    private int memberCode; // pk
    private String email; //
    private String username;
    private String password;
    private String authCode; // auto generated
    private int authStatus; // 0 = not authorized, 1=authorized

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberCode=" + memberCode +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authCode='" + authCode + '\'' +
                ", authorized=" + authStatus +
                '}';
    }

    public MemberDTO(int memberCode, String email, String username, String password, String authCode, int authStatus) {
        this.memberCode = memberCode;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authCode = authCode;
        this.authStatus = authStatus;
    }

    public MemberDTO() {
    }

    public int getMemberNo() {
        return memberCode;
    }

    public void setMemberNo(int memberNo) {
        this.memberCode = memberNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }
}
