package oncall.domain.calendar;

import java.time.DayOfWeek;

public class OnCallDate {

    private final int month;
    private final int day;
    private final DayOfWeek dayOfWeek;
    private final boolean isHoliday;

    public OnCallDate(int month, int day, DayOfWeek dayOfWeek, boolean isHoliday) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.isHoliday = isHoliday;
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

    public boolean isHoliday() {
        return isHoliday;
    }
}
