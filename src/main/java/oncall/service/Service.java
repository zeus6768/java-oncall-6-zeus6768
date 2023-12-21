package oncall.service;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oncall.domain.calendar.OnCallCalendar;
import oncall.domain.calendar.OnCallDate;
import oncall.domain.calendar.OnCallDayOfWeek;
import oncall.domain.crew.Crew;
import oncall.domain.crew.Crews;
import oncall.domain.oncall.OnCallList;

public class Service {

    public OnCallCalendar createCalendar(List<String> monthAndDayOfWeek) {
        Month month = Month.of(Integer.parseInt(monthAndDayOfWeek.get(0)));
        String dayOfWeek = monthAndDayOfWeek.get(1);
        DayOfWeek startDayOfWeek = OnCallDayOfWeek.findDayOfWeekByName(dayOfWeek);
        return new OnCallCalendar(month, startDayOfWeek);
    }

    public Crews createCrews(List<String> crewNames) {
        List<Crew> crews = crewNames.stream().map(Crew::new).toList();
        return new Crews(crews);
    }

    public OnCallList createOnCallList(OnCallCalendar calendar, Crews weekdayCrews, Crews holidayCrews) {
        List<String> weekdayNames = getNames(weekdayCrews);
        List<String> holidayNames = getNames(holidayCrews);

        Map<OnCallDate, String> orderResult = new LinkedHashMap<>();

        String prevName = null;

        for (OnCallDate onCallDate : calendar.getOnCallDates()) {
            String name;
            if (onCallDate.isWeekend() || onCallDate.isWeekdayAndHoliday()) {
                name = getNextName(holidayNames, prevName);
                orderResult.put(onCallDate, name);
                prevName = name;
                continue;
            }
            if (onCallDate.isWeekday()) {
                name = getNextName(weekdayNames, prevName);
                orderResult.put(onCallDate, name);
                prevName = name;
            }
        }
        return new OnCallList(orderResult);
    }

    private List<String> getNames(Crews crews) {
        List<String> crewNames = crews.stream().map(Crew::name).toList();
        List<String> result = new ArrayList<>();
        while (result.size() < 30) {
            result.addAll(crewNames);
        }
        return result;
    }

    private String getNextName(List<String> names, String prevName) {
        String name = names.get(0);
        if (name.equals(prevName)) {
            swapFirstTwoElements(names);
        }
        return names.remove(0);
    }

    private void swapFirstTwoElements(List<String> list) {
        if (list == null || list.size() <= 1) {
            throw new IllegalArgumentException("비상 근무일을 배치할 수 없습니다.");
        }
        String temp = list.get(0);
        list.set(0, list.get(1));
        list.set(1, temp);
    }
}
