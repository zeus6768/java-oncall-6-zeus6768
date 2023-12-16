package oncall.domain.calendar;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnCallCalendar {

    private final int month;
    private final List<Integer> weekdays;
    private final List<Integer> holidays;

    public OnCallCalendar(int month, DayOfWeek startDay) {
        this.month = month;
        this.weekdays = new ArrayList<>();
        this.holidays = new ArrayList<>();
        initialize(startDay);
    }

    private void initialize(DayOfWeek startDay) {
        int lastDayOfMonth = Month.getLastDayOfMonth(month);
        int currentDayOfWeek = startDay.getValue();
        for (int i = 1; i <= lastDayOfMonth; i++) {
            if (isGivenHoliday(currentDayOfWeek, i)) {
                holidays.add(i);
                currentDayOfWeek = (currentDayOfWeek % 7) + 1;
                continue;
            }
            weekdays.add(i);
            currentDayOfWeek = (currentDayOfWeek % 7) + 1;
        }
    }

    private boolean isGivenHoliday(int currentDayOfWeek, int absoluteDay) {
        return currentDayOfWeek == DayOfWeek.SATURDAY.getValue()
                || currentDayOfWeek == DayOfWeek.SUNDAY.getValue()
                || OnCallHoliday.isHoliday(month, absoluteDay);
    }

    public boolean isWeekDay(int day) {
        return weekdays.contains(day);
    }

    public boolean isHoliday(int day) {
        return holidays.contains(day);
    }

    public int getMonth() {
        return month;
    }

    public List<Integer> getWeekdays() {
        return Collections.unmodifiableList(weekdays);
    }

    public List<Integer> getHolidays() {
        return Collections.unmodifiableList(holidays);
    }
}
