package oncall.view.output;

import oncall.domain.calendar.OnCallDate;
import oncall.domain.calendar.OnCallDayOfWeek;
import oncall.domain.crew.Crew;
import oncall.domain.oncall.OnCallList;

public class OutputView {

    public void printOnCallList(OnCallList result) {
        System.out.println();
        result.entryStream()
                .forEach(entry -> {
                    OnCallDate onCallDate = entry.getKey();
                    Crew crew = entry.getValue();
                    printOnCallDate(onCallDate, crew);
                });
    }

    private void printOnCallDate(OnCallDate onCallDate, Crew crew) {
        System.out.printf(
                "%d월 %d일 %s%s %s%n",
                onCallDate.month().getValue(),
                onCallDate.day(),
                getDayOfWeekMessage(onCallDate),
                getHolidayMessage(onCallDate),
                crew.name()
        );
    }

    private String getDayOfWeekMessage(OnCallDate onCallDate) {
        return OnCallDayOfWeek.findNameByDayOfWeek(onCallDate.dayOfWeek());
    }

    private String getHolidayMessage(OnCallDate onCallDate) {
        if (onCallDate.isWeekdayAndHoliday()) {
            return "(휴일)";
        }
        return "";
    }
}
