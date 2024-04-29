package mcp.myclassplanner.model.dto;

public class SectionDTO {
    private String start;
    private String end;
    private boolean[] days = new boolean[5]; // [0]

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean[] getDays() {
        return days;
    }

    public void setDays(boolean[] days) {
        this.days = days;
    }
}
