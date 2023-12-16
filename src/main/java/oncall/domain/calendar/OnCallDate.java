package oncall.domain.calendar;

public record OnCallDate(int month, int day) {

    @Override
    public String toString() {
        return month + "월 " + day + "일";
    }
}
