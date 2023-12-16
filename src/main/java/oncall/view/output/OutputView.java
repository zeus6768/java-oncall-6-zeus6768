package oncall.view.output;

import oncall.domain.calendar.OnCallDate;
import oncall.domain.oncall.OnCallResult;

public class OutputView {

    public void printOnCallResult(OnCallResult result) {
        System.out.println();
        result.entryStream()
                .forEach(entry -> {
                    OnCallDate date = entry.getKey();
                    String name = entry.getValue();
                    System.out.printf(
                            "%d월 %d일 %s%s %s%n",
                            date.getMonth(),
                            date.getDay(),
                            date.getDayOfWeek(),
                            getHolidayMessage(date), name
                    );
                });
    }

    private String getHolidayMessage(OnCallDate date) {
        if (date.isHoliday()) {
            return "(휴일)";
        }
        return "";
    }
}
