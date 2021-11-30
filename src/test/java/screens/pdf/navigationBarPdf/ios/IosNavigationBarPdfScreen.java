package screens.pdf.navigationBarPdf.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.navigationBarPdf.NavigationBarPdfScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosNavigationBarPdfScreen extends NavigationBarPdfScreen {
    private final IButton btnBack =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]"), "btnBack");
    private final IButton btnTocAndBookmarksAndGallery =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[2]"), "btnTocAndBookmarksAndGallery");
    private final IButton btnSearch =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[3]"), "btnSearch");
    private final IButton btnBookmarks =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[4]"), "btnBookmarks");

    public IosNavigationBarPdfScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar"));
    }


    @Override
    public void tapBackButton() {
        btnBack.click();
    }

    @Override
    public void openTocBookmarksGallery() {
        btnTocAndBookmarksAndGallery.click();
    }

    @Override
    public void tapSearchButton() {
        btnSearch.click();
    }

    @Override
    public void tapBookmarksButton() {
        btnBookmarks.click();
    }
}
