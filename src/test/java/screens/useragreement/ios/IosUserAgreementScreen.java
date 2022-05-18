package screens.useragreement.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.useragreement.UserAgreementScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosUserAgreementScreen extends UserAgreementScreen {

    private final String LINK = "//XCUIElementTypeLink[contains(@name, \"%s\")]";
    private final ILabel lblAgreement = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name, \"License Agreement\")]"), "Agreement");

    public IosUserAgreementScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar[@name=\"User Agreement\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblAgreement.state().isDisplayed();
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
