package screens.pdf.tocPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class TocPdfScreen extends Screen {
    public TocPdfScreen(By locator) {
        super(locator, "TocPdf");
    }

    public abstract boolean isTOCOpened();

    public abstract void openThumbnails();

    public abstract boolean areThumbnailDisplayed();

    public abstract int openRandomThumbnail();

    public abstract void returnToReaderPdfScreen();

    public abstract boolean isThumbnailOpened(int number);

    public abstract boolean isTOCClosed();







    public abstract void openChapter(String chapter);

    public abstract int getChapterNumber(String chapter);
}
