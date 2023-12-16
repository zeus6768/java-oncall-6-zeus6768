package oncall.service;

import java.time.DayOfWeek;
import java.util.List;

import oncall.domain.calendar.OnCallCalendar;
import oncall.domain.calendar.OnCallDayOfWeek;
import oncall.repository.Repository;

public class Service {

    private final Repository repository;

    public OnCallCalendar createCalendar(List<String> monthAndDayOfWeek) {
        int month = Integer.parseInt(monthAndDayOfWeek.get(0));
        String dayOfWeek = monthAndDayOfWeek.get(1);
        DayOfWeek startDayOfWeek = OnCallDayOfWeek.findByName(dayOfWeek);
        return new OnCallCalendar(month, startDayOfWeek);
    }
    public Service(Repository repository) {
        this.repository = repository;
    }
}
