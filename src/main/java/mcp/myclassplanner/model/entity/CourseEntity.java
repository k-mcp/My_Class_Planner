package mcp.myclassplanner.model.entity;

public class CourseEntity {
    private int courseNo;
    private int memberCode;
    private String courseName;
    private int startTime;
    private int endTime;
    private String days;

    public CourseEntity() {
    }

    public CourseEntity(int courseNo, int memberCode, String courseName, int startTime, int endTime, String days) {
        this.courseNo = courseNo;
        this.memberCode = memberCode;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseNo=" + courseNo +
                ", memberCode=" + memberCode +
                ", courseName='" + courseName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", days='" + days + '\'' +
                '}';
    }

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
