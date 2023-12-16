package oncall.domain.calendar;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnCallCalendar {

    private final int month;
    private final List<OnCallDay> weekdays;
    private final List<OnCallDay> weekends;

    public OnCallCalendar(int month, DayOfWeek startDay) {
        this.month = month;
        this.weekdays = new ArrayList<>();
        this.weekends = new ArrayList<>();
        initialize(startDay);
    }

    private void initialize(DayOfWeek startDay) {
        int lastDayOfMonth = Month.getLastDayOfMonth(month);
        int currentDayOfWeek = startDay.getValue();
        for (int i = 1; i <= lastDayOfMonth; i++) {
            OnCallDay onCallDay = new OnCallDay(month, i, DayOfWeek.of(currentDayOfWeek));
            if (isWeekend(currentDayOfWeek)) {
                weekends.add(onCallDay);
                currentDayOfWeek = (currentDayOfWeek % 7) + 1;
                continue;
            }
            weekdays.add(onCallDay);
            currentDayOfWeek = (currentDayOfWeek % 7) + 1;
        }
    }

    private boolean isWeekend(int currentDayOfWeek) {
        return currentDayOfWeek == DayOfWeek.SATURDAY.getValue()
                || currentDayOfWeek == DayOfWeek.SUNDAY.getValue();
    }

    public DayOfWeek getDayOfWeek(int day) {
        List<OnCallDay> days = new ArrayList<>(weekdays);
        days.addAll(weekends);
        return days.stream()
                .filter(onCallDay -> onCallDay.day() == day)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 날짜입니다."))
                .dayOfWeek();
    }

    public boolean isWeekDay(int day) {
        return weekdays.stream()
                .anyMatch(weekday -> weekday.day() == day);
    }

    public boolean isWeekEnd(int day) {
        return weekends.stream()
                .anyMatch(weekday -> weekday.day() == day);
    }

    public int getMonth() {
        return month;
    }

    public List<OnCallDay> getWeekdays() {
        return Collections.unmodifiableList(weekdays);
    }
}
