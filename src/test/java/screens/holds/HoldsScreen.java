package screens.holds;

import aquality.appium.mobile.screens.Screen;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;

public abstract class HoldsScreen extends Screen {
    protected HoldsScreen(By locator) {
        super(locator, "Holds");
    }

    public abstract boolean isNoBooksMessagePresent();

    public abstract boolean isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract void openBookWithSpecificTypeAndSpecificNameAndSpecificActionButton(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);
}
