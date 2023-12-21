package oncall.domain.crew;

import java.util.List;
import java.util.stream.Stream;

public class Crews {

    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        validateSize(crews);
        validateNotDuplicated(crews);
        this.crews = crews;
    }

    private void validateSize(List<Crew> crews) {
        if (crews.size() <= 5 || crews.size() > 35) {
            throw new IllegalArgumentException("근무자 수는 5명 이상, 35명 이하로 입력해주세요.");
        }
    }

    private void validateNotDuplicated(List<Crew> crews) {
        if ((int) crews.stream().distinct().count() != crews.size()) {
            throw new IllegalArgumentException("중복된 닉네임을 입력할 수 없습니다.");
        }
    }

    public Stream<Crew> stream() {
        return crews.stream();
    }
}
