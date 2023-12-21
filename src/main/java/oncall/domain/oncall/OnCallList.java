package oncall.domain.oncall;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import oncall.domain.calendar.OnCallDate;

public class OnCallList {

    private final Map<OnCallDate, String> onCallList;

    public OnCallList(Map<OnCallDate, String> onCallList) {
        this.onCallList = onCallList;
    }

    public Stream<Entry<OnCallDate, String>> entryStream() {
        return onCallList.entrySet().stream();
    }
}
