package screens.pdf.settingspdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class SettingsPdfScreen extends Screen {
    protected SettingsPdfScreen(By locator) {
        super(locator, "Settings screen");
    }

    public abstract boolean isOpened();

    public abstract void tapGoToFirstPage();

    public abstract void tapGoToLastPage();

    public abstract void tapVerticalScrolling();

    public abstract boolean isVerticalScrollingChosen();

    public abstract boolean isNoSpreadsAvailable();

    public abstract boolean isOddSpreadsAvailable();

    public abstract boolean isEvenSpreadsAvailable();

    public abstract void tapHorizontalScrolling();

    public abstract boolean isHorizontalScrollingChosen();

    public abstract void tapWrappedScrolling();

    public abstract boolean isWrappedScrollingChosen();
}
