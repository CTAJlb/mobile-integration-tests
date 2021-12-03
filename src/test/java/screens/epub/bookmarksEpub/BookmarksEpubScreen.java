package screens.epub.bookmarksEpub;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;

public abstract class BookmarksEpubScreen extends Screen {
    public BookmarksEpubScreen(By locator) {
        super(locator, "BookmarksEpub");
    }

public abstract List<String> getListOfBookmarkTitles();

public abstract List<String> getListOfBookmarkTimeDates();

public abstract void deleteBookmark(int bookmarkNumber);

public abstract void openBookmark(int bookmarkNumber);

public abstract boolean isBookmarkPresent(String bookmarkTitle, String bookmarkDateTime);
}
