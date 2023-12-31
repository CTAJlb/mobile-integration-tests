package screens.alert;

import aquality.appium.mobile.screens.Screen;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;

public abstract class AlertScreen extends Screen {
    protected AlertScreen(By locator) {
        super(locator, "AlertScreen");
    }

    public abstract void waitAndPerformAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys actionButtonNamesAlertKeys);

    public abstract void performAlertActionIfDisplayedInSpanish(String actionBtnKey);
    public abstract void performAlertActionIfDisplayedInItalian(String actionBtnKey);

    public abstract String getTextFromAlertHeader();
}
