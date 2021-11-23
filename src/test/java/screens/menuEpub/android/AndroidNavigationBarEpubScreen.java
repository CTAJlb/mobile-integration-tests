package screens.menuEpub.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.menuEpub.NavigationBarEpubScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidNavigationBarEpubScreen extends NavigationBarEpubScreen {
    private final IButton btnFontSettings =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuSettings\")]"), "btnFontSettings");
    private final IButton btnTOC =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuTOC\")]"), "btnChapters");
    private final IButton btnBookmark =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuAddBookmark\")]"), "btnBookmark");
    private final IButton btnBack =
            getElementFactory().getButton(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"readerToolbar\")]/android.widget.ImageButton"), "btnBack");

    public AndroidNavigationBarEpubScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"readerToolbar\")]"));
    }

    @Override
    public void returnToPreviousScreen() {
        btnBack.click();
    }

    @Override
    public void clickFontSettingsButton() {
        btnFontSettings.click();
    }

    @Override
    public void clickBookmarkButton() {
        btnBookmark.click();
    }

    @Override
    public void clickTOCButton() {
        btnTOC.click();
    }
}
