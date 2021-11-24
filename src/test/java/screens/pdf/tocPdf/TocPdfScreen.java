package screens.pdf.tocPdf;

import aquality.appium.mobile.screens.Screen;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import org.openqa.selenium.By;

import java.util.Set;

public abstract class TocPdfScreen extends Screen {
    protected TocPdfScreen(By locator) {
        super(locator, "Table of Contents");
    }

    public abstract void switchToChaptersListView();

    public abstract Set<String> getListOfBookChapters();

    public abstract void openChapter(String chapter);

    public abstract void clickResumeButton();

    public abstract int getChapterPageNumber(String chapter);

    public abstract boolean isGalleryPagesLoaded();

    public abstract int getCountOfBookPages();

    public abstract void scrollGallery(EntireElementSwipeDirection entireElementSwipeDirection);

    public abstract void openGalleryPage(int pageNumber);
}
