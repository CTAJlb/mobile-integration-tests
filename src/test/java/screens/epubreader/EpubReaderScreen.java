package screens.epubreader;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;
import screens.menuEpub.NavigationBarEpubScreen;

import java.util.List;

public abstract class EpubReaderScreen extends Screen {

    protected EpubReaderScreen(By locator) {
        super(locator, "Reader");
    }

    public abstract String getBookName();

    public abstract void openNavigationBar();

    public abstract void hideNavigationBar();

    public abstract String getChapterName();

    public abstract void clickLeftCorner();

    public abstract void clickRightCorner();

    public abstract String getPageNumber();

    public abstract void openFontSettings();

    public abstract void openToc();

    public abstract double getFontSize();

    public abstract String getFontName();

    public abstract String getBackgroundColor();

    public abstract void returnToPreviousScreen();
}
