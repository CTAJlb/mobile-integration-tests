package screens.epubreader;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Set;

public abstract class EpubReaderScreen extends Screen {
    protected EpubReaderScreen(By locator) {
        super(locator, "Reader");
    }

    public abstract String getBookName();

    public abstract String getChapterName();

    public abstract boolean isBookNamePresent();

    public abstract void swipeFromLeftToRight();

    public abstract void swipeFromRightToLeft();

    public abstract void clickLeftCorner();

    public abstract void clickRightCorner();

    public abstract String getPageNumber();

    public abstract List<String> getListOfChapters();

    public abstract void openChapter(String chapter);

    public abstract void openFontSettings();

    public abstract void openTableOfContents();

    public abstract double getFontSize();

    public abstract String getFontName();

    public abstract String getFontColor();

    public abstract String getFontAndBackgroundColor();

    public abstract void waitForBookLoading();

    public abstract void returnToPreviousScreen();
}
