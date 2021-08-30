package screens.alert;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;

public abstract class AlertScreen extends Screen {
    protected AlertScreen(By locator) {
        super(locator, "AlertScreen");
    }

    public abstract void performAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys actionButtonNamesAlertKeys);
}
