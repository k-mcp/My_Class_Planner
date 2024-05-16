package mcp.myclassplanner.model.dao;

import mcp.myclassplanner.model.dto.PlanDTO;
import mcp.myclassplanner.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {

    void generatePro(ScheduleDTO possible);

    List<ScheduleDTO> viewAll();

    void truncate();

    List<ScheduleDTO> viewResult();

    void addNewPlan(String s);

    List<PlanDTO> viewMyPlan(int memberCode);

    void deletePlan(String caseNo);

    Integer getLastCaseNo(int memberCode);
}
