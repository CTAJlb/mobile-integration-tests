package screens.epub.navigationBarEpub.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.epub.navigationBarEpub.NavigationBarEpubScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosNavigationBarEpubScreen extends NavigationBarEpubScreen {
    private final IButton btnFontSettings = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name=\"Reader settings\"]"), "Font Settings");
    private final IButton btnTOC = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[2]"), "Table of contents");
    private final IButton btnAddBookmark = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name=\"Add Bookmark\"]"), "Add Bookmark");
    private final IButton btnDeleteBookmark = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name=\"Remove Bookmark\"]"), "Delete Bookmark");
    private final IButton btnBack = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]"), "btnBack");

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
