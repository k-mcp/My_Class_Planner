package mcp.myclassplanner.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.List;
import java.util.Timer;

public class SectionDTO {
    private int startTime;
    private int endTime;
    private String days = "";

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "SectionDTO{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", days=" + days +
                '}';
    }

    // getters and setters
}
