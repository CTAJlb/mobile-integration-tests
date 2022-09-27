package screens.pdf.tocbookmarkspdf.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.bookmarksPdf.BookmarksPdfScreen;
import screens.pdf.thumbnailspdf.ThumbnailsPdfScreen;
import screens.pdf.tocbookmarkspdf.TocBookmarksPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosTocBookmarksPdfScreen extends TocBookmarksPdfScreen {
    private final TocPdfScreen tocPdfScreen;
    private final ThumbnailsPdfScreen thumbnailsPdfScreen;
    private final BookmarksPdfScreen bookmarksPdfScreen;

    private final IButton btnResume =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Resume\"]"), "Resume button");
    private final IButton btnThumbnails =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[@name=\"Mission Control\"]"), "Thumbnails button");
    private final IButton btnToc =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[@name=\"List\"]"), "Toc button");
    private final IButton btnBookmarks =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[@name=\"Bookmark\"]"), "Bookmarks button");

    public IosTocBookmarksPdfScreen() {
        super(By.xpath("//XCUIElementTypeButton[@name = \"Resume\"]"));
        tocPdfScreen = AqualityServices.getScreenFactory().getScreen(TocPdfScreen.class);
        thumbnailsPdfScreen = AqualityServices.getScreenFactory().getScreen(ThumbnailsPdfScreen.class);
        bookmarksPdfScreen = AqualityServices.getScreenFactory().getScreen(BookmarksPdfScreen.class);
    }

    @Override
    public TocPdfScreen getTocPdfScreen() {
        return tocPdfScreen;
    }

    @Override
    public BookmarksPdfScreen getBookmarksPdfScreen() {
        return bookmarksPdfScreen;
    }

    @Override
    public ThumbnailsPdfScreen getThumbnailsPdfScreen() {
        return thumbnailsPdfScreen;
    }

    @Override
    public boolean isThumbnailsButtonDisplayed() {
        return btnThumbnails.state().waitForDisplayed();
    }

    @Override
    public boolean isChaptersButtonDisplayed() {
        return btnToc.state().waitForDisplayed();
    }

    @Override
    public boolean isTextContentOpened() {
        //for android
        return false;
    }

    @Override
    public void returnToReaderPdfScreen() {
        btnResume.click();
    }

    @Override
    public boolean isTocClosed() {
        return btnThumbnails.state().waitForNotDisplayed();
    }

    @Override
    public void tapBookmarksButton() {
        btnBookmarks.click();
    }

    @Override
    public void tapResumeButton() {
        btnResume.click();
    }

    @Override
    public void tapThumbnailsButton() {
        btnThumbnails.click();
    }

    @Override
    public void tapTocButton() {
        btnToc.click();
    }
}
