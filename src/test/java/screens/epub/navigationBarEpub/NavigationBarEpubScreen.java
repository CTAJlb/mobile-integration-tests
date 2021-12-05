package screens.epub.navigationBarEpub;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class NavigationBarEpubScreen extends Screen {
    public NavigationBarEpubScreen(By locator) {
        super(locator, "NavigationBarEpub");
    }

    public abstract void returnToPreviousScreen();

    public abstract void tapFontSettingsButton();

    public abstract void tapAddBookmarkButton();

    public abstract void tapDeleteBookmarkButton();

    public abstract void tapTOCBookmarksButton();

    public abstract boolean isBookmarkDisplayed();
}
