package oncall.controller;

import java.util.List;
import java.util.function.Supplier;

import oncall.domain.calendar.OnCallCalendar;
import oncall.domain.oncall.OnCallResult;
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
        OnCallResult result = createOrder(calendar);
        printResult(result);
    }

    private OnCallCalendar createCalendar() {
        return runWithExceptionHandler(() -> {
            List<String> monthAndDayOfWeek = runWithExceptionHandler(inputView::askMonthAndDayOfWeek);
            return service.createCalendar(monthAndDayOfWeek);
        });
    }

    private OnCallResult createOrder(OnCallCalendar calendar) {
        List<String> weekDayOrderNames = inputOrder(inputView::askWeekdayOrder);
        List<String> holidayOrderNames = inputOrder(inputView::askHolidayOrder);
        return service.createOrder(calendar, weekDayOrderNames, holidayOrderNames);
    }

    private List<String> inputOrder(Supplier<List<String>> orderInputCallback) {
        return runWithExceptionHandler(() -> service.validateOrderNames(orderInputCallback.get()));
    }

    private void printResult(OnCallResult result) {
        outputView.printOnCallResult(result);
    }

    private <T> T runWithExceptionHandler(Supplier<T> callback) {
        return exceptionHandler.run(callback);
    }
}
