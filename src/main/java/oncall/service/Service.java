package oncall.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oncall.domain.calendar.Month;
import oncall.domain.calendar.OnCallCalendar;
import oncall.domain.calendar.OnCallDate;
import oncall.domain.calendar.OnCallDayOfWeek;
import oncall.domain.calendar.GivenHoliday;
import oncall.domain.crew.Crew;
import oncall.domain.crew.Crews;
import oncall.domain.oncall.OnCallResult;

public class Service {

    public OnCallCalendar createCalendar(List<String> monthAndDayOfWeek) {
        int month = Integer.parseInt(monthAndDayOfWeek.get(0));
        String dayOfWeek = monthAndDayOfWeek.get(1);
        DayOfWeek startDayOfWeek = OnCallDayOfWeek.findByName(dayOfWeek);
        return new OnCallCalendar(month, startDayOfWeek);
    }

    public Crews createCrews(List<String> crewNames) {
        List<Crew> crews = crewNames.stream().map(Crew::new).toList();
        return new Crews(crews);
    }

    public OnCallResult createOrder(OnCallCalendar calendar, Crews weekdayCrews, Crews holidayCrews) {
        List<String> weekdayNames = initNames(weekdayCrews);
        List<String> holidayNames = initNames(holidayCrews);

        Map<OnCallDate, String> orderResult = new LinkedHashMap<>();

        int month = calendar.getMonth();
        int lastDayOfMonth = Month.getLastDayOfMonth(month);
        String prevName = null;
        for (int day = 1; day <= lastDayOfMonth; day++) {
            boolean isWeekDayAndHoliday = GivenHoliday.isHoliday(month, day) && calendar.isWeekDay(day);
            OnCallDate date = new OnCallDate(month, day, calendar.getDayOfWeek(day), isWeekDayAndHoliday);
            String name;
            if (calendar.isWeekEnd(day) || GivenHoliday.isHoliday(month, day)) {
                name = getNextName(holidayNames, prevName);
                orderResult.put(date, name);
                prevName = name;
                continue;
            }
            if (calendar.isWeekDay(day)) {
                name = getNextName(weekdayNames, prevName);
                orderResult.put(date, name);
                prevName = name;
            }
        }
        return new OnCallResult(orderResult);
    }

    private List<String> initNames(Crews crews) {
        List<String> crewNames = crews.crews().stream().map(Crew::name).toList();
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
