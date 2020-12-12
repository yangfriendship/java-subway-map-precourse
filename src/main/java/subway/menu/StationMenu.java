package subway.menu;

import subway.controller.ControllerFactory;
import subway.controller.SubwayController;
import subway.exception.MenuNotFountException;
import subway.view.OutputView;

import java.util.Arrays;

public enum StationMenu implements Menu {

    REGISTER("1", "역 등록", Action.REGISTER),
    DELETE("2", "역 삭제", Action.DELETE),
    SEARCH("3", "역 조회", Action.SEARCH),
    BACK("B", "돌아가기", Action.BACK);

    final String order;
    final String menu;
    final Action action;

    private static final String MENU_TITLE = "역 관리 ";
    private static SubwayController controller = ControllerFactory.of(StationMenu.DELETE);
    private static final String MENU_TYPE = "역";

    StationMenu(String order, String menu, Action action) {
        this.order = order;
        this.menu = menu;
        this.action = action;
    }

    @Override
    public Menu run() {

        try {
            action.action(controller);
            return MainMenu.LINE;
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return StationMenu.BACK;
        }
    }

    @Override
    public String toString() {
        return order + ". " + menu;
    }

    @Override
    public Menu[] getValues() {
        return values();
    }

    @Override
    public String getTitle() {
        return MENU_TITLE;
    }

    @Override
    public Menu change(String command) {
        return Arrays
                .stream(values())
                .filter(menu -> menu.order.equals(command))
                .findFirst().orElseThrow(() ->
                        new MenuNotFountException(command)
                );
    }

    @Override
    public String getType() {
        return MENU_TYPE;
    }

    @Override
    public String getActionType() {
        return action.toString();
    }
}
