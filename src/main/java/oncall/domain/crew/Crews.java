package oncall.domain.crew;

import java.util.Collections;
import java.util.List;

public record Crews(List<Crew> crews) {

    public Crews {
        validateSize(crews);
        validateNotDuplicated(crews);
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

    @Override
    public List<Crew> crews() {
        return Collections.unmodifiableList(crews);
    }
}
