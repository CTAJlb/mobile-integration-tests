package screens.pdf.readerPdf;

import aquality.appium.mobile.screens.Screen;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.openqa.selenium.By;
import screens.pdf.tocPdf.TocPdfScreen;

public abstract class ReaderPdfScreen extends Screen {
    protected ReaderPdfScreen(By locator) {
        super(locator, "Pdf reader");
    }

    public abstract String getBookName();

    public abstract int getPageNumber();

    public abstract void openMenu();

    public abstract void goToNextPage();

    public abstract void goToPreviousPage();

    public abstract void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection);

    public abstract TocPdfScreen openGallery();

    public abstract void openSearchPdf();

    public abstract void closeReader();

    public abstract void openTableOfContents();
}
