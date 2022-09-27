package screens.pdf.thumbnailspdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class ThumbnailsPdfScreen extends Screen {
    public ThumbnailsPdfScreen(By locator) {
        super(locator, "ThumbnailsPdf");
    }

    public abstract boolean areThumbnailsDisplayed();

    public abstract int openRandomThumbnail();
}
