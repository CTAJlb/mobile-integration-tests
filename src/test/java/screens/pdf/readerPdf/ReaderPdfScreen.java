package screens.pdf.readerPdf;

import aquality.appium.mobile.screens.Screen;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.openqa.selenium.By;
import screens.pdf.navigationBarPdf.NavigationBarPdfScreen;

public abstract class ReaderPdfScreen extends Screen {
    protected ReaderPdfScreen(By locator) {
        super(locator, "Pdf reader");
    }

    public abstract String getBookName();

    public abstract int getPageNumber();

    public abstract void goToNextPage();

    public abstract void goToPreviousPage();

    public abstract void clickToc();

    public abstract void returnToPreviousScreen();

    public abstract NavigationBarPdfScreen getNavigationBarPdfScreen();

    public abstract void openAdditionalButtonsAndLabels();

    public abstract void hideAdditionalButtonsAndLabels();

    public abstract void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection);
}
