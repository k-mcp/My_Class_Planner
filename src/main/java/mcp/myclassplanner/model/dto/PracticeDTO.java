package mcp.myclassplanner.model.dto;

public class PracticeDTO {
    String start;
    String end;

    public PracticeDTO() {
    }

    public PracticeDTO(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "PracticeDTO{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

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
}
