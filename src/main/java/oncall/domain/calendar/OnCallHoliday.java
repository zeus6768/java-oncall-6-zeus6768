package oncall.domain.calendar;

import java.util.Arrays;

public enum OnCallHoliday {

    NEW_YEAR(1, 1),
    SAMILJEOL(3, 1),
    CHILDRENS_DAY(5, 5),
    MEMORIAL_DAY(6, 6),
    LIBERATION_DAY(8, 15),
    NATIONAL_FOUNDATION_DAY(10, 3),
    HANGUL_DAY(10, 9),
    CHRISTMAS(12, 25);

    private final int month;
    private final int day;

    OnCallHoliday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(int month, int day) {
        return Arrays.stream(values()).anyMatch(holiday -> holiday.month == month && holiday.day == day);
    }
}
