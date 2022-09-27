package screens.pdf.tocbookmarkspdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;
import screens.pdf.bookmarksPdf.BookmarksPdfScreen;
import screens.pdf.thumbnailspdf.ThumbnailsPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

public abstract class TocBookmarksPdfScreen extends Screen {
    public TocBookmarksPdfScreen(By locator) {
        super(locator, "tocBookmarksGalleryPdf");
    }

    public abstract TocPdfScreen getTocPdfScreen();

    public abstract BookmarksPdfScreen getBookmarksPdfScreen();

    public abstract ThumbnailsPdfScreen getThumbnailsPdfScreen();

    public abstract boolean isThumbnailsButtonDisplayed();

    public abstract boolean isChaptersButtonDisplayed();

    public abstract boolean isTextContentOpened();

    public abstract void returnToReaderPdfScreen();

    public abstract boolean isTocClosed();

    public abstract void tapBookmarksButton();

    public abstract void tapResumeButton();

    public abstract void tapThumbnailsButton();

    public abstract void tapTocButton();
}
