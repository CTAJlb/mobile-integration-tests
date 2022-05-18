package screens.softwarelicenses;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class SoftwareLicScreen extends Screen {

    protected SoftwareLicScreen(By locator) {
        super(locator, "Software Licenses");
    }

    public abstract boolean isOpened();

    public abstract void scrollThePage(String  link);

    public abstract boolean isLinkAvailable(String link);
}
