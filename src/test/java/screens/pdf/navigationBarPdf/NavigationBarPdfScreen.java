package screens.pdf.navigationBarPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class NavigationBarPdfScreen extends Screen {
    protected NavigationBarPdfScreen(By locator) {
        super(locator, "NavigationBarPdf");
    }

    public abstract void returnToPreviousScreen();

    public abstract void clickTocAndBookmarksAndGalleryButton();

    public abstract void clickSearchButton();

    public abstract void clickBookmarksButton();
}
