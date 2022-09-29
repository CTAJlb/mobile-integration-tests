package screens.pdf.tocbookmarkspdf.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.bookmarksPdf.BookmarksPdfScreen;
import screens.pdf.thumbnailspdf.ThumbnailsPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;
import screens.pdf.tocbookmarkspdf.TocBookmarksPdfScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocBookmarksPdfScreen extends TocBookmarksPdfScreen {
    private final IButton btnThumbnails = getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"viewThumbnail\"]"), "TOC with thumbnails");
    private final IButton btnChapters = getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"viewOutline\"]"), "Chapter content");
    private final IButton btnOnTheRight = getElementFactory().getButton(By.xpath("//android.view.View[@resource-id=\"outlineOptionsContainer\"]"), "Button on the right corner");

    private final TocPdfScreen tocPdfScreen;
    private final ThumbnailsPdfScreen thumbnailsPdfScreen;

    public AndroidTocBookmarksPdfScreen(){
        super(By.xpath("//android.view.View[@resource-id=\"toolbarSidebar\"]"));
        tocPdfScreen = AqualityServices.getScreenFactory().getScreen(TocPdfScreen.class);
        thumbnailsPdfScreen = AqualityServices.getScreenFactory().getScreen(ThumbnailsPdfScreen.class);
    }

    @Override
    public TocPdfScreen getTocPdfScreen() {
        return tocPdfScreen;
    }

    @Override
    public BookmarksPdfScreen getBookmarksPdfScreen() {
        //only for ios
        return null;
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
        return btnChapters.state().waitForDisplayed();
    }

    @Override
    public boolean isTextContentOpened() {
        return btnOnTheRight.state().waitForDisplayed();
    }

    @Override
    public void returnToReaderPdfScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public boolean isTocClosed() {
        return btnThumbnails.state().waitForNotDisplayed();
    }

    @Override
    public void tapBookmarksButton() {
        //only for ios
    }

    @Override
    public void tapResumeButton() {
        //only for ios
    }

    @Override
    public void tapThumbnailsButton() {
        btnThumbnails.click();
    }

    @Override
    public void tapTocButton() {
        btnChapters.click();
    }
}
