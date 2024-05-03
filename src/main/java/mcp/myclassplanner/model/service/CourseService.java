package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dao.CourseMapper;
import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.dto.SectionDTO;
import mcp.myclassplanner.model.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper){

        this.courseMapper = courseMapper;
    }

    public int addCourse(CourseDTO courseDTO) {
        for(SectionDTO sectionDTO: courseDTO.getSectionDTOList()){
            CourseEntity course = new CourseEntity();
            course.setCourseName(courseDTO.getCourseName());
            course.setStartTime(sectionDTO.getStartTime());
            course.setEndTime(sectionDTO.getEndTime());
            course.setDays(sectionDTO.getDays());
            courseMapper.addCourse(course);
        }
        return 1;
    }
}
