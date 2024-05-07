package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {

    void generatePro(ScheduleDTO possible);

    List<ScheduleDTO> viewAll();

    void truncate();
}
