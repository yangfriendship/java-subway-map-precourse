package subway.menu;

import subway.exception.MenuNotFountException;

import java.util.Arrays;

public enum StationMenu implements Menu {

    REGISTER("1", "역 등록"),
    DELETE("2", "역 삭제"),
    SEARCH("3", "역 조회"),
    BACK("B", "돌아가기");

    final String order;
    final String menu;

    private static final String title = "역 관리 ";

    StationMenu(String order, String menu) {
        this.order = order;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return order + ". " + menu;
    }
    @Override
    public Menu run() {
        return null;
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
