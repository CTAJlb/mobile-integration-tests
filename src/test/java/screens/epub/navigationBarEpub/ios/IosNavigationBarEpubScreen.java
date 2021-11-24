package screens.epub.navigationBarEpub.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.epub.navigationBarEpub.NavigationBarEpubScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosNavigationBarEpubScreen extends NavigationBarEpubScreen {
    private final IButton btnFontSettings = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[3]"), "btnFontSettings");
    private final IButton btnTOC =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[2]"), "btnChapters");
    private final IButton btnBookmark =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[4]"), "btnBookmark");
    private final IButton btnBack =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]"), "btnBack");

    public IosNavigationBarEpubScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar"));
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
    public void clickTOCAndBookmarksButton() {
        btnTOC.click();
    }
}
