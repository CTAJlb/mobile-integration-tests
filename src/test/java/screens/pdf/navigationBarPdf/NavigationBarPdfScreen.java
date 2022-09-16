package screens.pdf.navigationBarPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class NavigationBarPdfScreen extends Screen {
    protected NavigationBarPdfScreen(By locator) {
        super(locator, "NavigationBarPdf");
    }

    public abstract void tapBackButton();

    public abstract void openTOC();





    public abstract void openTocBookmarksGallery();

    public abstract void tapSearchButton();

    public abstract void tapAddBookmarkButton();

    public abstract void tapDeleteBookmarkButton();

    public abstract boolean isBookmarkDisplayed();
}
