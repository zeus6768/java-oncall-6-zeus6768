package oncall.domain.oncall;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import oncall.domain.calendar.OnCallDate;

public class OnCallResult {

    private final Map<OnCallDate, String> result;

    public OnCallResult(Map<OnCallDate, String> result) {
        this.result = result;
    }

    public Stream<Entry<OnCallDate, String>> entryStream() {
        return result.entrySet().stream();
    }
}
