package oncall.controller;

import java.util.List;
import java.util.function.Supplier;

import oncall.domain.calendar.OnCallCalendar;
import oncall.domain.crew.Crews;
import oncall.domain.oncall.OnCallList;
import oncall.exception.ExceptionHandler;
import oncall.service.Service;
import oncall.view.input.InputView;
import oncall.view.output.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Service service;
    private final ExceptionHandler exceptionHandler;

    public Controller(InputView inputView, OutputView outputView, Service service, ExceptionHandler exceptionHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.service = service;
        this.exceptionHandler = exceptionHandler;
    }

    public void run() {
        OnCallCalendar calendar = createCalendar();
        OnCallList result = createOnCallList(calendar);
        printOnCallList(result);
    }

    private OnCallCalendar createCalendar() {
        return runWithExceptionHandler(() -> {
            List<String> monthAndDayOfWeek = runWithExceptionHandler(inputView::askMonthAndDayOfWeek);
            return service.createCalendar(monthAndDayOfWeek);
        });
    }

    private OnCallList createOnCallList(OnCallCalendar calendar) {
        Crews weekdayCrews = createCrews(inputView::askWeekdayOrder);
        Crews holidayCrews = createCrews(inputView::askHolidayOrder);
        return service.createOnCallList(calendar, weekdayCrews, holidayCrews);
    }

    private Crews createCrews(Supplier<List<String>> orderInputCallback) {
        return runWithExceptionHandler(() -> {
            List<String> names = orderInputCallback.get();
            return service.createCrews(names);
        });
    }

    private void printOnCallList(OnCallList result) {
        outputView.printOnCallList(result);
    }

    private <T> T runWithExceptionHandler(Supplier<T> callback) {
        return exceptionHandler.run(callback);
    }
}
