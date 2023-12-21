package oncall.exception;

import java.time.DateTimeException;
import java.util.function.Supplier;

public class ExceptionHandler {

    private static final String ERROR = "[ERROR] ";
    private static final String INPUT_ERROR = "잘못된 입력입니다.";

    public <T> T run(Supplier<T> callback) {
        while (true) {
            try {
                return callback.get();
            } catch (NumberFormatException | DateTimeException exception) {
                System.out.printf("%s%s%n%n", ERROR, INPUT_ERROR);
            } catch (IllegalArgumentException exception) {
                System.out.printf("%s%s%n%n", ERROR, exception.getMessage());
            }
        }
    }
}
