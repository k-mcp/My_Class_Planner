package mcp.myclassplanner.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum McpRole {

    USER("USER"),
    ADMIN("ADMIN"),
    ALL("USER,ADMIN");

    private String role;


}
