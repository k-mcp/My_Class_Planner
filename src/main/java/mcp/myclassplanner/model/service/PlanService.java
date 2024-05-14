package mcp.myclassplanner.model.service;

import mcp.myclassplanner.model.dao.PlanMapper;
import mcp.myclassplanner.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {
    static List<ScheduleDTO> schedule;
    static List<ScheduleDTO> possible;
    static TimetableDTO[] timetable;
    static int caseNo = 1;

    private final PlanMapper planMapper;

    @Autowired
    public PlanService(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }
    public void generate(List<CourseDTO> courseDTOList, int depth, int memberCode){
        if(depth == courseDTOList.size()){
            List<ScheduleDTO> list = new ArrayList<>(schedule);
            for(ScheduleDTO scheduleDTO : list){
                ScheduleDTO s = new ScheduleDTO();
                s.setMemberCode(scheduleDTO.getMemberCode());
                s.setCaseNo(caseNo);
                s.setCourseName(scheduleDTO.getCourseName());
                s.setStart(scheduleDTO.getStart());
                s.setEnd(scheduleDTO.getEnd());
                s.setDays(scheduleDTO.getDays());
                possible.add(s);
            }
            caseNo++;
            return;
        }
        CourseDTO course = courseDTOList.get(depth);
        for(int i = 0; i < course.getSectionDTOList().size(); i++){
            SectionDTO section = course.getSectionDTOList().get(i);
            if(!isConflictCheck(section)){
                ScheduleDTO scheduleDTO = new ScheduleDTO();

                scheduleDTO.setCourseName(course.getCourseName());
                scheduleDTO.setDays(section.getDays());
                scheduleDTO.setStart(section.getStartTime());
                scheduleDTO.setEnd(section.getEndTime());
                scheduleDTO.setMemberCode(memberCode);

                schedule.add(scheduleDTO);
                generate(courseDTOList, depth+1, memberCode);
                removeTimetable(section);
                schedule.remove(schedule.size()-1);
            }
        }
    }
    public void removeTimetable(SectionDTO sectionDTO){
        String days = sectionDTO.getDays();
        if(days.contains("M")){
            timetable[0].getTimes().remove(timetable[0].getTimes().size()-1);
        }
        if (days.contains("T")){
            timetable[1].getTimes().remove(timetable[1].getTimes().size()-1);
        }
        if(days.contains("W")){
            timetable[2].getTimes().remove(timetable[2].getTimes().size()-1);
        }
        if (days.contains("X")) {
            timetable[3].getTimes().remove(timetable[3].getTimes().size()-1);
        }
        if (days.contains("F")){
            timetable[4].getTimes().remove(timetable[4].getTimes().size()-1);
        }

    }
    public boolean isConflictCheck(SectionDTO section){
        if(section.getDays().contains("Th")){
            section.setDays(section.getDays().replace("Th", "X"));
        }
        String days = section.getDays();
        boolean isConflict = false;
                if(days.contains("M")){
                   isConflict = simulator(section.getStartTime(),section.getEndTime(), timetable[0]);
                   if(isConflict) return true;
                }
                if (days.contains("T")){
                    isConflict = simulator(section.getStartTime(),section.getEndTime(), timetable[1]);
                    if(isConflict) return true;
                }
                if(days.contains("W")){
                    isConflict = simulator(section.getStartTime(),section.getEndTime(), timetable[2]);
                    if(isConflict) return true;
                }
                if (days.contains("X")) {
                    isConflict = simulator(section.getStartTime(),section.getEndTime(), timetable[3]);
                    if(isConflict) return true;
                }
                if (days.contains("F")){
                    isConflict = simulator(section.getStartTime(),section.getEndTime(), timetable[4]);
                    if(isConflict) return true;
                }
                TimeDTO time = new TimeDTO();
                time.setStart(section.getStartTime());
                time.setEnd(section.getEndTime());
            if(days.contains("M")){
                timetable[0].getTimes().add(time);
            }
            if(days.contains("T")){
                timetable[1].getTimes().add(time);
            }
        if(days.contains("W")){
            timetable[2].getTimes().add(time);
        }
        if(days.contains("X")){
            timetable[3].getTimes().add(time);
        }
        if(days.contains("F")){
            timetable[4].getTimes().add(time);
        }
        return isConflict;
    }
    public boolean simulator(int start, int end, TimetableDTO timetable) {
        for (TimeDTO time : timetable.getTimes()) {
            //첫 번째 조건: 새로운 일정이 기존 일정의 시작 시간보다 작거나 같고, 끝나는 시간보다 작은 조건 + 새로운일정의 끝나는 시간이 기존 일종의 시작시간보다 커야함
            if ((time.getStart() < start && time.getEnd() < end && time.getEnd() > start) ||
                    // 1100 <= 900
                    //두 번째 조건: 새로운 일정이 기존 일정의 시작 시간보다 크거나 같고, 끝나는 시간보다 크거나 같은 조건
                    (time.getStart() > start && time.getEnd() > end && time.getStart() < end) ||

                    //세 번째 조건: 새로운 일정이 기존 일정의 시작 시간보다 크거나 같고, 끝나는 시간보다 작거나 같은 조건
                    (time.getStart() >= start && time.getEnd() <= end) ||

                    //마지막 조건: 새로운 일정이 기존 일정의 시작 시간보다 작거나 같고, 끝나는 시간보다 작은 조건입
                    (time.getStart() <= start && time.getEnd() >= end)) {
                return true;
            }
        }
        return false;
    }
    public void generatePro(List<CourseDTO> courses, int memberCode) {
        schedule = new ArrayList<>();
        possible = new ArrayList<>();
        timetable = new TimetableDTO[5];
        for(int i =0; i< 5; i++){
            timetable[i] = new TimetableDTO();
        }
        generate(courses, 0, memberCode);
        planMapper.truncate();
        for(ScheduleDTO scheduleDTO : possible){
            planMapper.generatePro(scheduleDTO);
        }


    }

    public List<ScheduleDTO> viewResult() {
        return planMapper.viewResult();
    }

    public void addNewPlan(String s) {
        planMapper.addNewPlan(s);
    }

    public List<PlanDTO> viewMyPlan(int memberCode) {
        return planMapper.viewMyPlan(memberCode);
    }

    public void deletePlan(String i) {
        planMapper.deletePlan(i);
    }
}



