package oncall.domain.calendar;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnCallCalendar {

    protected static final List<DayOfWeek> WEEKEND_DAY_OF_WEEKS = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    private final Month month;
    private final List<OnCallDate> onCallDates;

    public OnCallCalendar(Month month, DayOfWeek startDay) {
        this.month = month;
        onCallDates = new ArrayList<>();
        initialize(startDay);
    }

    private void initialize(DayOfWeek startDayOfWeek) {
        int lastDayOfMonth = month.minLength();
        int dayOfWeekValue = startDayOfWeek.getValue();
        for (int day = 1; day <= lastDayOfMonth; day++) {
            DayOfWeek dayOfWeek = DayOfWeek.of(dayOfWeekValue);
            OnCallDate date = new OnCallDate(month, day, dayOfWeek, isWeekdayAndHoliday(dayOfWeek, day));
            onCallDates.add(date);
            dayOfWeekValue = (dayOfWeekValue % 7) + 1;
        }
    }

    private boolean isWeekdayAndHoliday(DayOfWeek dayOfWeek, int day) {
        return !WEEKEND_DAY_OF_WEEKS.contains(dayOfWeek) && Holiday.isHoliday(month, day);
    }

    public int countWeekdaysExceptHolidays() {
        return (int) onCallDates.stream().filter(OnCallDate::isWeekday).count() - countWeekdayHolidays();
    }

    public int countHolidays() {
        return (int) onCallDates.stream().filter(OnCallDate::isWeekend).count() + countWeekdayHolidays();
    }

    public int countWeekdayHolidays() {
        return (int) onCallDates.stream().filter(OnCallDate::isWeekdayAndHoliday).count();
    }
    public List<OnCallDate> getOnCallDates() {
        return Collections.unmodifiableList(onCallDates);
    }
}
