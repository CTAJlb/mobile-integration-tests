package screens.menuEpub;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class NavigationBarEpubScreen extends Screen {
    public NavigationBarEpubScreen(By locator) {
        super(locator, "NavigationBarEpub");
    }

    public abstract void returnToPreviousScreen();
    public abstract void clickFontSettingsButton();
    public abstract void clickBookmarkButton();
    public abstract void clickTOCButton();
}
