package mcp.myclassplanner.model.dto;

import java.util.List;


public class CourseDTO {
    private String courseName;
    private List<SectionDTO> sectionDTOList;

    public CourseDTO() {
    }

    public CourseDTO(String courseName, List<SectionDTO> sectionDTOList) {
        this.courseName = courseName;
        this.sectionDTOList = sectionDTOList;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<SectionDTO> getSectionDTOList() {
        return sectionDTOList;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseName='" + courseName + '\'' +
                ", sectionDTOList=" + sectionDTOList +
                '}';
    }

    public void setSectionDTOList(List<SectionDTO> sectionDTOList) {
        this.sectionDTOList = sectionDTOList;
    }
}

