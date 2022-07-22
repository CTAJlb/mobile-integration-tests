package screens.softwarelicenses.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.softwarelicenses.SoftwareLicScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosSoftwareLicScreen extends SoftwareLicScreen {

    private final String LINK = "//XCUIElementTypeLink[contains(@name, \"%s\")]";
    private final ILabel lblLicense = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"Palace License\"]"), "Palace License");

    public IosSoftwareLicScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar[@name=\"Software Licenses\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblLicense.state().waitForDisplayed();
    }

    @Override
    public void scrollThePage(String link) {
        while (!isLinkDisplayed(link)) {
            SwipeElementUtils.swipeByCoordinatesOfWindow();
        }
    }

    @Override
    public boolean isLinkAvailable(String link) {
        ILabel lblLink = getElementFactory().getLabel(By.xpath(String.format(LINK, link)), "Link");
        return lblLink.state().isClickable();
    }

    private boolean isLinkDisplayed(String link) {
        ILabel lblLink = getElementFactory().getLabel(By.xpath(String.format(LINK, link)), "Link");
        return lblLink.state().isDisplayed();
    }
}
