package oncall.view.input;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String askSubject() {
        System.out.println("입력하세요 : ");
        return readLine();
    }

    private String readLine() {
        return Console.readLine().trim();
    }

    private List<String> split(String input) {
        return List.of(input.split("\\s*,\\s*"));
    }
}
