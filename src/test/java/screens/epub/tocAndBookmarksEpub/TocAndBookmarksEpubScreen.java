package screens.epub.tocAndBookmarksEpub;

import aquality.appium.mobile.screens.Screen;
import constants.epub.EnumTabsTocAndBookmarksEpub;
import org.openqa.selenium.By;
import screens.epub.tocEpub.TocEpubScreen;

public abstract class TocAndBookmarksEpubScreen extends Screen {
    protected TocEpubScreen tocEpubScreen;

    public TocAndBookmarksEpubScreen(By locator) {
        super(locator, "TocEpub");
    }

    public abstract void returnToPreviousScreen();

    public abstract void openTab(EnumTabsTocAndBookmarksEpub tab);

    public abstract TocEpubScreen getTocEpubScreen();
}
