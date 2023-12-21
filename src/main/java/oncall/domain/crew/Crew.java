package oncall.domain.crew;

public record Crew(String name) {

    public Crew {
        validate(name);
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("사원 닉네임은 5글자 이하로 입력해주세요.");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
