package oncall.domain.calendar;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;

class OnCallCalendarTest {

    @Test
    void calendarTest() {
        List<String> monthAndDayOfWeek = List.of("5", "월");
        int month = Integer.parseInt(monthAndDayOfWeek.get(0));
        String dayOfWeek = monthAndDayOfWeek.get(1);
        DayOfWeek startDayOfWeek = OnCallDayOfWeek.findByName(dayOfWeek);
        OnCallCalendar calendar = new OnCallCalendar(month, startDayOfWeek);
        System.out.println(calendar.getWeekdays());
        System.out.println(calendar.getHolidays());
        assertThat(calendar.getWeekdays())
                .isEqualTo(List.of(1, 2, 3, 4, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 22, 23, 24, 25, 26, 29, 30, 31));
        assertThat(calendar.getHolidays())
                .isEqualTo(List.of(5, 6, 7, 13, 14, 20, 21, 27, 28));
    }
}
