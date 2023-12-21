package oncall.domain.oncall;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import oncall.domain.calendar.OnCallDate;
import oncall.domain.crew.Crew;

public class OnCallList {

    private final Map<OnCallDate, Crew> onCallList;

    public OnCallList(Map<OnCallDate, Crew> onCallList) {
        this.onCallList = onCallList;
    }

    public Stream<Entry<OnCallDate, Crew>> entryStream() {
        return onCallList.entrySet().stream();
    }
}
