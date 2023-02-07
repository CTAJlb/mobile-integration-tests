package screens.epub.tocBookmarksEpub;

import aquality.appium.mobile.screens.Screen;
import enums.epub.EnumTabsTocAndBookmarksEpub;
import org.openqa.selenium.By;
import screens.epub.bookmarksEpub.BookmarksEpubScreen;
import screens.epub.tocEpub.TocEpubScreen;

public abstract class TocBookmarksEpubScreen extends Screen {
    protected TocEpubScreen tocEpubScreen;
    protected BookmarksEpubScreen bookmarksEpubScreen;

    public TocBookmarksEpubScreen(By locator) {
        super(locator, "TocBookmarksEpub");
    }

    public abstract void returnToPreviousScreen();

    public abstract void openTab(EnumTabsTocAndBookmarksEpub tab);

    public abstract TocEpubScreen getTocEpubScreen();

    public abstract BookmarksEpubScreen getBookmarksEpubScreen();

    public abstract String getTextFromBackBtn();

    public abstract String getTextFromTOCLabel();

    public abstract String getTextFromContentsBtn();

    public abstract String getTextFromBookmarksBtn();
}
