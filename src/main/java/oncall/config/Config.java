package oncall.config;

import oncall.controller.Controller;
import oncall.exception.ExceptionHandler;
import oncall.service.Service;
import oncall.view.input.InputView;
import oncall.view.output.OutputView;

public class Config {

    private static Config config;

    private Controller controller;
    private Service service;
    private InputView inputView;
    private OutputView outputView;
    private ExceptionHandler exceptionHandler;

    private Config() {}

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public Controller controller() {
        if (controller == null) {
            controller = new Controller(
                    inputView(),
                    outputView(),
                    service(),
                    exceptionHandler()
            );
        }
        return controller;
    }

    public Service service() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public ExceptionHandler exceptionHandler() {
        if (exceptionHandler == null) {
            exceptionHandler = new ExceptionHandler();
        }
        return exceptionHandler;
    }
}
