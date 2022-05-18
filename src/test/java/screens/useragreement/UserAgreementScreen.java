package screens.useragreement;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class UserAgreementScreen extends Screen {

    protected UserAgreementScreen(By locator) {
        super(locator, "User Agreement");
    }

    public abstract boolean isOpened();

    public abstract void scrollThePage(String  link);

    public abstract boolean isLinkAvailable(String link);
}
