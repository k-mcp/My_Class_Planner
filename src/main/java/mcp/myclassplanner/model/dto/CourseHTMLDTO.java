package mcp.myclassplanner.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseHTMLDTO {
    private String courseName;
    private String startTime;
    private String endTime;
    private List<String> M;
    private List<String> T;
    private String W;
    private String Th;
    private String F;

    @Override
    public String toString() {
        return "CourseHTMLDTO{" +
                "courseName='" + courseName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", M=" + M +
                ", T=" + T +
                ", W='" + W + '\'' +
                ", Th='" + Th + '\'' +
                ", F='" + F + '\'' +
                '}';
    }

    public CourseHTMLDTO() {
    }
}

