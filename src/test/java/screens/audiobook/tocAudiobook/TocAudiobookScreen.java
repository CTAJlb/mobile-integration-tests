package screens.audiobook.tocAudiobook;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class TocAudiobookScreen extends Screen {
    protected TocAudiobookScreen(By locator) {
        super(locator, "TocAudiobookScreen");
    }

    public abstract boolean isTheFirstChapterLoaded();

    public abstract String openChapterAndGetChapterName(int chapterNumber);

    public abstract int getCountOfChapters();

    public abstract String getChapterName(int chapterNumber);

    public abstract String getTextFromBackBtn();

    public abstract void clickBackBtn();

    public abstract boolean isContentTabDisplayed();

    public abstract boolean isBookmarksTabDisplayed();

    public abstract void openBookmarks();

    public abstract void openChapters();

    public abstract boolean isChaptersSelected();
}
