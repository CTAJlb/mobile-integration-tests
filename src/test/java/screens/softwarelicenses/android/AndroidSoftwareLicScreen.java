package screens.softwarelicenses.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.softwarelicenses.SoftwareLicScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSoftwareLicScreen extends SoftwareLicScreen {

    private final String LINK = "//android.widget.TextView[contains(@text, \"%s\")]";
    private final ILabel lblLicense = getElementFactory().getLabel(By.xpath("//android.widget.TextView[@text=\"Palace License\"]"), "Palace License");

    public AndroidSoftwareLicScreen() {
        super(By.xpath("//android.widget.TextView[@text=\"Software Licenses\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblLicense.state().isDisplayed();
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
