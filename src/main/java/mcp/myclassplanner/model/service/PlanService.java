//package mcp.myclassplanner.model.service;
//
//import mcp.myclassplanner.model.dto.CourseDTO;
//import mcp.myclassplanner.model.dto.SectionDTO;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class PlanService {
//    static List<SectionDTO> schedule = new ArrayList<>();
//    static List<List<SectionDTO>> possible = new ArrayList<>();
//    static List<List<SectionDTO>> timetable = new ArrayList<>(5);
//
//    public void generate(List<CourseDTO> courseDTOList, int depth){
//        if(depth == courseDTOList.size()){
//            possible.add(new ArrayList<>(schedule));
//            return;
//        }
//        CourseDTO course = courseDTOList.get(depth);
//        for(int i = 0; i < course.getSectionDTOList().size(); i++){
//            if(timetableCheck(course.getSectionDTOList().get(i))){
//
//                schedule.add(course.getSectionDTOList().get(i));
//                generate(courseDTOList, depth+1);
//                schedule.remove(schedule.size()-1);
//            }
//        }
//    }
//
//    public boolean timetableCheck(SectionDTO section){
//        for(int i = 0; i < schedule.size(); i++){
//            for(int j = 0; j < 5; i++){
//                if(section.getDays()[j]){
//                    String start = section.getStart();
//                    String end = section.getEnd();
//                    simulator(start,end,j);
//                }
//            }
//
//        }
//        return true;
//    }
//
//public static void simulator(int start, int end, int index) {
//    List<SectionDTO> list = timetable.get(index);
//
//    for (SectionDTO section : list) {
//        //첫 번째 조건: 새로운 일정이 기존 일정의 시작 시간보다 작거나 같고, 끝나는 시간보다 작은 조건 + 새로운일정의 끝나는 시간이 기존 일종의 시작시간보다 커야함
//        if ((section.getStart() < start && section.getEnd() < end && section.getEnd() > start) ||
//                // 1100 <= 900
//                //두 번째 조건: 새로운 일정이 기존 일정의 시작 시간보다 크거나 같고, 끝나는 시간보다 크거나 같은 조건
//                (section.getStart() > start && section.getEnd() > end && section.getStart() < end) ||
//
//                //세 번째 조건: 새로운 일정이 기존 일정의 시작 시간보다 크거나 같고, 끝나는 시간보다 작거나 같은 조건
//                (section.getStart() >= start && section.getEnd() <= end) ||
//
//                //마지막 조건: 새로운 일정이 기존 일정의 시작 시간보다 작거나 같고, 끝나는 시간보다 작은 조건입
//                (section.getStart() <= start && section.getEnd() >= end)) {
//
//            return;
//        }
//    }
//}
//
//}
