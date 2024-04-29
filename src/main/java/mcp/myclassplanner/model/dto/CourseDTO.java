package mcp.myclassplanner.model.dto;

import java.util.List;


public class CourseDTO {
    private String courseName;
    private List<SectionDTO> sectionDTOList;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<SectionDTO> getSectionDTOList() {
        return sectionDTOList;
    }

    public void setSectionDTOList(List<SectionDTO> sectionDTOList) {
        this.sectionDTOList = sectionDTOList;
    }
}
