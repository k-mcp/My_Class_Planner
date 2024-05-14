package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    void addCourse(CourseEntity courseEntity);

    List<CourseEntity> viewAllCourse(int memberCode);

    void deleteCourse(String courseName, int memberCode);
}
