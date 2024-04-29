package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.dto.SectionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {
    static List<SectionDTO> schedule = new ArrayList<>();
    static List<List<SectionDTO>> possible = new ArrayList<>();
    static List<List<SectionDTO>> timetable = new ArrayList<>();

    public void generate(List<CourseDTO> courseDTOList, int depth){
        if(depth == courseDTOList.size()){
            possible.add(new ArrayList<>(schedule));
            return;
        }
        CourseDTO course = courseDTOList.get(depth);
        for(int i = 0; i < course.getSectionDTOList().size(); i++){
            if(timetableCheck(course.getSectionDTOList().get(i))){
                schedule.add(course.getSectionDTOList().get(i));
                generate(courseDTOList, depth+1);
                schedule.remove(schedule.size()-1);
            }
        }
    }

    public boolean timetableCheck(SectionDTO section){
        for(int i = 0; i < schedule.size(); i++){
            for(int j = 0; j <5; i++){
                if(section.getDays()[i]){
                    String start = section.getStart();
                    String end = section.getEnd();

                }
            }

        }
        return true;
    }

    public static void simulator(String start, String end, int index){
        List<SectionDTO> list = timetable.get(index);
        boolean crush = false;
        for(SectionDTO section : list){
            if(section.getStart() < start && section.getEnd() < end
            || section.getEnd() < start && section.get)

        }
    }

}
