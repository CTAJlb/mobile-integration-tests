package screens.privacypolicy;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class PrivacyPolicyScreen extends Screen {

    protected PrivacyPolicyScreen(By locator) {
        super(locator, "Privacy Policy");
    }

    public abstract boolean isOpened();
}
