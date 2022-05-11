package screens.aboutpalace;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class AboutPalaceScreen extends Screen {
    protected AboutPalaceScreen(By locator) {
        super(locator, "About Palace");
    }

    public abstract boolean isOpened();
}
