package mcp.myclassplanner.model.dto;

public class PlanDTO {
    private int planNo;
    private int memberCode;
    private int caseNo;
    private String courseName;
    private int start;
    private int end;
    private String days;

    public PlanDTO() {
    }

    @Override
    public String toString() {
        return "PlanDTO{" +
                "planNo=" + planNo +
                ", memberCode=" + memberCode +
                ", caseNo=" + caseNo +
                ", courseName='" + courseName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", days='" + days + '\'' +
                '}';
    }

    public int getPlanNo() {
        return planNo;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
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

    public PlanDTO(int planNo, int memberCode, int caseNo, String courseName, int start, int end, String days) {
        this.planNo = planNo;
        this.memberCode = memberCode;
        this.caseNo = caseNo;
        this.courseName = courseName;
        this.start = start;
        this.end = end;
        this.days = days;
    }
}
