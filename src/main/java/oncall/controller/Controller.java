package oncall.controller;

import java.util.function.Supplier;

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
    }

    private <T> T runWithExceptionHandler(Supplier<T> callback) {
        return exceptionHandler.run(callback);
    }
}
