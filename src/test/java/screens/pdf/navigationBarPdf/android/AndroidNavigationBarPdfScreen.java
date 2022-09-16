package screens.pdf.navigationBarPdf.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.navigationBarPdf.NavigationBarPdfScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidNavigationBarPdfScreen extends NavigationBarPdfScreen {
    private final IButton btnBack =
            getElementFactory().getButton(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"), "Back button");
    private final IButton btnToc =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuTOC\")]"), "Table of content");

    public AndroidNavigationBarPdfScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"pdf_toolbar\")]"));
    }

    @Override
    public void tapBackButton() {
        btnBack.click();
    }

    @Override
    public void openTOC() {
        btnToc.click();
    }


    @Override
    public void openTocBookmarksGallery() {

    }

    @Override
    public void tapSearchButton() {

    }

    @Override
    public void tapAddBookmarkButton() {

    }

    @Override
    public void tapDeleteBookmarkButton() {

    }

    @Override
    public boolean isBookmarkDisplayed() {
        return false;
    }
}
