package screens.epub.readerEpub;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;
import screens.epub.navigationBarEpub.NavigationBarEpubScreen;

public abstract class ReaderEpubScreen extends Screen {
    protected NavigationBarEpubScreen navigationBarEpubScreen;

    protected ReaderEpubScreen(By locator) {
        super(locator, "ReaderEpub");
    }

    public abstract String getBookName();

    public abstract NavigationBarEpubScreen getNavigationBarEpubScreen();

    public abstract void openNavigationBar();

    public abstract void hideNavigationBar();

    public abstract String getChapterName();

    public abstract void clickLeftCorner();

    public abstract void clickRightCorner();

    public abstract String getPageNumber();

    public abstract double getFontSize();

    public abstract String getFontName();

    public abstract String getBackgroundColor();
}
