package oncall.domain.calendar;

import static oncall.domain.calendar.OnCallCalendar.WEEKEND_DAY_OF_WEEKS;

import java.time.DayOfWeek;
import java.time.Month;

public record OnCallDate(Month month, int day, DayOfWeek dayOfWeek, boolean isWeekdayAndHoliday) {

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return WEEKEND_DAY_OF_WEEKS.contains(dayOfWeek);
    }
}
