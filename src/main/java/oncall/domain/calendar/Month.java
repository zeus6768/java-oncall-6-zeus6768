package oncall.domain.calendar;

import java.util.Arrays;
import java.util.List;

public enum Month {
    THIRTY_ONE_DAYS(31, List.of(1, 3, 5, 7, 8, 10, 12)),
    THIRTY_DAYS(30, List.of(4, 6, 9, 11)),
    TWENTY_EIGHT_DAYS(28, List.of(2));

    private final int lastDay;
    private final List<Integer> months;

    Month(int lastDay, List<Integer> months) {
        this.lastDay = lastDay;
        this.months = months;
    }

    public static int getLastDayOfMonth(int month) {
        return Arrays.stream(values())
                .filter(type -> type.months.contains(month))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 월입니다."))
                .lastDay;
    }
}
