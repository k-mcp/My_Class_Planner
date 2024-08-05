package mcp.myclassplanner.auth.model.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class LoginDto {
    private String id;
    private String pass;
}
