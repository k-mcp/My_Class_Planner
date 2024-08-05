package mcp.myclassplanner.model.dto;



import lombok.Data;
import mcp.myclassplanner.common.McpRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class User {

    private int userNo;

    private String userId;

    private String userPass;

    private String userName;

    private String userEmail;

    private McpRole roleList;

    private String state;

}
