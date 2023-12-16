package oncall.view.input;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public List<String> askMonthAndDayOfWeek() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        return split(readLine());
    }

    public List<String> askWeekdayOrder() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return split(readLine());
    }

    private String readLine() {
        return Console.readLine().trim();
    }

    private List<String> split(String input) {
        return List.of(input.split("\\s*,\\s*"));
    }
}
