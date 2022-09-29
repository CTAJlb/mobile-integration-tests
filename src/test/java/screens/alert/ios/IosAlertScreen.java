package screens.alert.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;
import screens.alert.AlertScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosAlertScreen extends AlertScreen {
    private static final String UNIQUE_ELEMENT_LOC = "//XCUIElementTypeAlert";
    private static final String ACTION_BUTTON_LOC = UNIQUE_ELEMENT_LOC + "//XCUIElementTypeButton[@name=\"%s\"]";

    public IosAlertScreen() {
        super(By.xpath(UNIQUE_ELEMENT_LOC));
    }

    @Override
    public void waitAndPerformAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys actionButtonNamesAlertKeys) {
        IButton actionButton = getElementFactory().getButton(By.xpath(String.format(ACTION_BUTTON_LOC, actionButtonNamesAlertKeys.i18n())), String.format("%s ActionButtonAlert", actionButtonNamesAlertKeys.i18n()));
        if(actionButton.state().waitForDisplayed()){
            actionButton.click();
        }
    }
}
