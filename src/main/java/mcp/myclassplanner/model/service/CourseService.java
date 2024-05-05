package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dao.CourseMapper;
import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.dto.SectionDTO;
import mcp.myclassplanner.model.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper){

        this.courseMapper = courseMapper;
    }

    public void addCourse(CourseDTO courseDTO) {
        for(SectionDTO sectionDTO: courseDTO.getSectionDTOList()){
            CourseEntity course = new CourseEntity();
            course.setMemberCode(courseDTO.getMemberCode());
            course.setCourseName(courseDTO.getCourseName());
            course.setStartTime(sectionDTO.getStartTime());
            course.setEndTime(sectionDTO.getEndTime());
            course.setDays(sectionDTO.getDays());
            courseMapper.addCourse(course);
        }
    }

    public List<CourseDTO> viewAllCourse(int memberCode) {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseDTO courseDTO = new CourseDTO(); // courseDTO init
        List<SectionDTO> sectionDTOList = new ArrayList<>(); // sectionDTO init
        SectionDTO sectionDTO;
        List<CourseEntity> courses = courseMapper.viewAllCourse(memberCode);
        for(CourseEntity course : courses){
            sectionDTO = new SectionDTO();
            sectionDTO.setStartTime(course.getStartTime());
            sectionDTO.setEndTime(course.getEndTime());
            sectionDTO.setDays(course.getDays());
            if(courseDTO.getCourseName().equals(course.getCourseName())){
                // 만약 coursename 이 같으면
                sectionDTOList.add(sectionDTO);
            } else{
                courseDTOList.add(courseDTO);
                courseDTO = new CourseDTO();
                courseDTO.setCourseName(course.getCourseName());
            }
        }
        return null;
    }
}
