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
    private final IButton btnAddBookmark =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name=\"Add Bookmark\"]"), "btnAddBookmark");
    private final IButton btnDeleteBookmark =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name=\"Remove Bookmark\"]"), "btnDeleteBookmark");
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
    public void tapFontSettingsButton() {
        btnFontSettings.click();
    }

    @Override
    public void tapAddBookmarkButton() {
        btnAddBookmark.click();
    }

    @Override
    public void tapDeleteBookmarkButton() {
        btnDeleteBookmark.click();
    }

    @Override
    public void tapTOCBookmarksButton() {
        btnTOC.click();
    }

    @Override
    public boolean isBookmarkDisplayed() {
        return btnDeleteBookmark.state().isDisplayed();
    }
}
