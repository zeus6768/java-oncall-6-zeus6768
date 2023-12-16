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
import oncall.domain.calendar.OnCallHoliday;
import oncall.domain.oncall.OnCallResult;
import oncall.repository.Repository;

public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public OnCallCalendar createCalendar(List<String> monthAndDayOfWeek) {
        int month = Integer.parseInt(monthAndDayOfWeek.get(0));
        String dayOfWeek = monthAndDayOfWeek.get(1);
        DayOfWeek startDayOfWeek = OnCallDayOfWeek.findByName(dayOfWeek);
        return new OnCallCalendar(month, startDayOfWeek);
    }

    public OnCallResult createOrder(OnCallCalendar calendar, List<String> weekDayOrderNames,
                                    List<String> holidayOrderNames) {
        List<String> weekdayNames = initNames(weekDayOrderNames);
        List<String> holidayNames = initNames(holidayOrderNames);

        Map<OnCallDate, String> orderResult = new LinkedHashMap<>();

        int month = calendar.getMonth();
        int lastDayOfMonth = Month.getLastDayOfMonth(month);
        String prevName = null;
        for (int day = 1; day <= lastDayOfMonth; day++) {
            OnCallDate date = new OnCallDate(month, day, calendar.getDayOfWeek(day), OnCallHoliday.isHoliday(month, day));
            String name = null;
            if (calendar.isWeekDay(day)) {
                name = getNextName(weekdayNames, prevName);
            }
            if (calendar.isHoliday(day)) {
                name = getNextName(holidayNames, prevName);
            }
            orderResult.put(date, name);
            prevName = name;
        }
        return new OnCallResult(orderResult);
    }

    private List<String> initNames(List<String> names) {
        List<String> result = new ArrayList<>();
        while (result.size() < 30) {
            result.addAll(names);
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
