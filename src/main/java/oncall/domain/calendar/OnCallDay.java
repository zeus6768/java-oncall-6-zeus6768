package oncall.domain.calendar;

import java.time.DayOfWeek;

public record OnCallDay(int month, int day, DayOfWeek dayOfWeek) {
}
