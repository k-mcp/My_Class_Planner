package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {

    int addCourse(CourseEntity courseEntity);
}
