package oncall.domain.calendar;

import java.time.DayOfWeek;

public class OnCallDate {

    private final int month;
    private final int day;
    private final DayOfWeek dayOfWeek;
    private final boolean isWeekDayAndHoliday;

    public OnCallDate(int month, int day, DayOfWeek dayOfWeek, boolean isWeekDayAndHoliday) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.isWeekDayAndHoliday = isWeekDayAndHoliday;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return OnCallDayOfWeek.findByDayOfWeek(dayOfWeek);
    }

    public boolean isWeekDayAndHoliday() {
        return isWeekDayAndHoliday;
    }
}
