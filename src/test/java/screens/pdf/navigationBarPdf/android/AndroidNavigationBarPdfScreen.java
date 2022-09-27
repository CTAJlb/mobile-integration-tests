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
    private final IButton btnSettings =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[@content-desc=\"Settings\"]"), "Settings");

    public AndroidNavigationBarPdfScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"pdf_toolbar\")]"));
    }

    @Override
    public void tapTocBookmarksBarButton() {
        btnToc.click();
    }

    @Override
    public void tapBackButton() {
        btnBack.click();
    }

    @Override
    public void tapSettingsButton() {
        btnSettings.click();
    }

    @Override
    public void tapSearchButton() {
        //only for ios
    }

    @Override
    public void tapBookmarkButton() {
        //only for ios
    }
}
