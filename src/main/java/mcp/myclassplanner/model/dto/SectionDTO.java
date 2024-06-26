package mcp.myclassplanner.model.dto;

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

    public SectionDTO() {
    }

    public SectionDTO(int startTime, int endTime, String days) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
    }
    // getters and setters
}
