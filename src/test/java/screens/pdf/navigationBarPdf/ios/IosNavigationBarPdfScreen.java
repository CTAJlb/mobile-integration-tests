package screens.pdf.navigationBarPdf.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.navigationBarPdf.NavigationBarPdfScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosNavigationBarPdfScreen extends NavigationBarPdfScreen {
    private final IButton btnToc = getElementFactory().getButton(By.xpath("//XCUIElementTypeOther[@name=\"Back\"]//XCUIElementTypeButton[@name=\"List\"]"), "Table of content button");
    private final IButton btnBack = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name=\"Back\"]"), "Back button");
    private final IButton btnSearch = getElementFactory().getButton(By.xpath("//XCUIElementTypeOther[@name=\"Search\"]//XCUIElementTypeButton[@name=\"Search\"]"), "Search button");
    private final IButton btnBookmark = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name=\"Bookmark\"]"), "Bookmark button");

    public IosNavigationBarPdfScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar"));
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
        //only for android
    }

    @Override
    public void tapSearchButton() {
        btnSearch.click();
    }

    @Override
    public void tapBookmarkButton() {
        btnBookmark.state().waitForDisplayed();
        btnBookmark.click();
    }
}
