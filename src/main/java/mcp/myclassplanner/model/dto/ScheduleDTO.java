package mcp.myclassplanner.model.dto;

public class ScheduleDTO {
    private int scheduleNo;
    private int memberCode;
    private int caseNo;
    private String courseName;
    private int start;
    private int end;
    private String days;

    public ScheduleDTO() {
    }

    public int getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(int scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getCaseNo() {
        return caseNo;
    }

    public ScheduleDTO(int scheduleNo, int memberCode, int caseNo, String courseName, int start, int end, String days) {
        this.scheduleNo = scheduleNo;
        this.memberCode = memberCode;
        this.caseNo = caseNo;
        this.courseName = courseName;
        this.start = start;
        this.end = end;
        this.days = days;
    }

    public void setCaseNo(int caseNo) {
        this.caseNo = caseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
