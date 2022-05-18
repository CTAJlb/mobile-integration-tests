package screens.useragreement.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.useragreement.UserAgreementScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidUserAgreementsScreen extends UserAgreementScreen {

    private final String LINK = "//android.widget.TextView[contains(@text, \"%s\")]";
    private final ILabel lblAgreement = getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@text, \"License Agreement\")]"), "Agreement");

    public AndroidUserAgreementsScreen() {
        super(By.xpath("android.widget.TextView[@text=\"User Agreement\"]"));
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
