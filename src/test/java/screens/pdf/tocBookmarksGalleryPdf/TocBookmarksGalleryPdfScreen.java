package screens.pdf.tocBookmarksGalleryPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;
import screens.pdf.bookmarksPdf.BookmarksPdfScreen;
import screens.pdf.galleryPdf.GalleryPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

public abstract class TocBookmarksGalleryPdfScreen extends Screen {
    public TocBookmarksGalleryPdfScreen(By locator) {
        super(locator, "tocBookmarksGalleryPdf");
    }

    public abstract void tapBackButton();

    public abstract void tapResumeButton();

    public abstract void tapGalleryButton();

    public abstract void tapBookmarksButton();

    public abstract TocPdfScreen getTocPdfScreen();

    public abstract BookmarksPdfScreen getBookmarksPdfScreen();

    public abstract GalleryPdfScreen getGalleryPdfScreen();

    public abstract void tapTocButton();
}
