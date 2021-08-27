package screens.alert;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.catalog.BookActionButtonKeys;
import org.openqa.selenium.By;

public abstract class AlertScreen extends Screen {
    protected AlertScreen(By locator) {
        super(locator, "Alert");
    }

    public abstract void closeNotNowModalIfDisplayed();

    //public abstract void performActionOnAlert(BookActionButtonKeys buttonName);

    public abstract void closeDoNotAllowIfPresent();
}
