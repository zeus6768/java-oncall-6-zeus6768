package oncall.domain.calendar;

import java.time.DayOfWeek;
import java.util.Arrays;

public enum OnCallDayOfWeek {
    MONDAY(DayOfWeek.MONDAY, "월"),
    TUESDAY(DayOfWeek.TUESDAY, "화" ),
    WEDNESDAY(DayOfWeek.WEDNESDAY, "수"),
    THURSDAY(DayOfWeek.THURSDAY, "목"),
    FRIDAY(DayOfWeek.FRIDAY, "금"),
    SATURDAY(DayOfWeek.SATURDAY, "토"),
    SUNDAY(DayOfWeek.SUNDAY, "일");

    private final DayOfWeek dayOfWeek;
    private final String name;

    OnCallDayOfWeek(DayOfWeek dayOfWeek, String name) {
        this.dayOfWeek = dayOfWeek;
        this.name = name;
    }

    public static String findByDayOfWeek(DayOfWeek dayOfWeek) {
        return Arrays.stream(values())
                .filter(day -> day.dayOfWeek.equals(dayOfWeek))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 요일입니다."))
                .name;
    }

    public static DayOfWeek findByName(String koreanName) {
        return Arrays.stream(values())
                .filter(dayOfWeek -> dayOfWeek.name.equals(koreanName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 요일입니다."))
                .dayOfWeek;
    }
}
