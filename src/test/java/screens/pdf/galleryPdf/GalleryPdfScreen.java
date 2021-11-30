package screens.pdf.galleryPdf;

import aquality.appium.mobile.screens.Screen;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import org.openqa.selenium.By;

public abstract class GalleryPdfScreen extends Screen {
    public GalleryPdfScreen(By locator) {
        super(locator, "GalleryPdf");
    }

    public abstract int getCountOfBookPages();

    public abstract void scrollGallery(EntireElementSwipeDirection entireElementSwipeDirection);

    public abstract void openGalleryPage(int pageNumber);
}
