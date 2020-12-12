package subway.menu;

import subway.Action;
import subway.controller.ControllerFactory;
import subway.controller.SubwayController;
import subway.exception.MenuNotFountException;

import java.util.Arrays;

public enum SectionMenu implements Menu {

    REGISTER("1", "구간 등록", Action.REGISTER),
    DELETE("2", "구간 삭제", Action.DELETE),
    BACK("B", "돌아가기", Action.BACK);

    final String order;
    final String menu;
    final Action action;

    private static final String title = "구간 관리 ";
    private static SubwayController controller = ControllerFactory.of(SectionMenu.BACK);

    SectionMenu(String order, String menu, Action action) {
        this.order = order;
        this.menu = menu;
        this.action = action;
    }

    @Override
    public Menu run() {

        try {
            action.action(controller);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return MainMenu.SECTION;
        }

        return null;
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
        return title;
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
}
