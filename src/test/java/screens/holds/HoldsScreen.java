package screens.holds;

import aquality.appium.mobile.screens.Screen;
import enums.EnumBookType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;

import java.util.List;

public abstract class HoldsScreen extends Screen {
    protected HoldsScreen(By locator) {
        super(locator, "Holds");
    }

    public abstract boolean isNoBooksMessagePresent();

    public abstract boolean isBookDisplayed(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract void openBook(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    public abstract boolean isHoldsScreenOpened();

    public abstract List<String> getListOfAuthors();

    public abstract List<String> getListOfTitles();

    public abstract String getNameOfSorting();

    public abstract void sortBy();

    public abstract String getTextFromHoldsHeader();

    public abstract String getTextFromInformationLbl();
}
