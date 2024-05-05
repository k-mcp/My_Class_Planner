package mcp.myclassplanner.model.dto;

import java.util.ArrayList;
import java.util.List;

public class TimetableDTO {
    List<TimeDTO> times = new ArrayList<>();



    public List<TimeDTO> getTimes() {
        return times;
    }

    public void setTimes(List<TimeDTO> times) {
        this.times = times;
    }

    public TimetableDTO() {
    }
}
