package screens.useragreement.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.useragreement.UserAgreementScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidUserAgreementsScreen extends UserAgreementScreen {

    private final ILabel lblAgreement = getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@text, \"License Agreement\")]"), "Agreement");

    public AndroidUserAgreementsScreen() {
        super(By.xpath("android.widget.TextView[@text=\"User Agreement\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblAgreement.state().isDisplayed();
    }
}
