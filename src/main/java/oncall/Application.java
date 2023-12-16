package oncall;

import oncall.config.Config;
import oncall.controller.Controller;

public class Application {
    public static void main(String[] args) {
        Config config = Config.getInstance();
        Controller controller = config.controller();
        controller.run();
    }
}
