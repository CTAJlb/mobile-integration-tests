package screens.pdf.bookmarksPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class BookmarksPdfScreen extends Screen {
    public BookmarksPdfScreen(By locator) {
        super(locator, "BookmarksPdf");
    }

    public abstract int getCountOfBookmarks();

    public abstract void openBookmarks(int bookmarksNumber);
}
