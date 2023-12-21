package oncall.domain.calendar;

import java.time.Month;
import java.util.Arrays;

public enum Holiday {
    NEW_YEAR(Month.JANUARY, 1),
    SAMILJEOL(Month.MARCH, 1),
    CHILDRENS_DAY(Month.MAY, 5),
    MEMORIAL_DAY(Month.JUNE, 6),
    LIBERATION_DAY(Month.AUGUST, 15),
    NATIONAL_FOUNDATION_DAY(Month.OCTOBER, 3),
    HANGUL_DAY(Month.OCTOBER, 9),
    CHRISTMAS(Month.DECEMBER, 25);

    private final Month month;
    private final int day;

    Holiday(Month month, int day) {
        this.month = month;
        this.day = day;
    }

    static boolean isHoliday(Month month, int day) {
        return Arrays.stream(values()).anyMatch(holiday -> holiday.month == month && holiday.day == day);
    }
}