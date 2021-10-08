package screens.holds.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.holds.HoldsScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidHoldsScreen extends HoldsScreen implements IWorkingWithListOfBooks {
    private static final String MAIN_ELEMENT_LOC = "//android.widget.TextView[@text=\"Holds\"]";
    private static final String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC = "//android.view.ViewGroup/android.widget.TextView[contains(@text,\"%s\")]/following-sibling::android.widget.LinearLayout//*[@text=\"%s\"]";
    private static final String SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC = SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC + "/parent::android.widget.LinearLayout/parent::android.view.ViewGroup/android.widget.TextView[1]";

    private final ILabel lblNoBooks = getElementFactory().getLabel(By.id("feedEmptyText"), "No Books Present");

    public AndroidHoldsScreen() {
        super(By.xpath(MAIN_ELEMENT_LOC));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().waitForDisplayed();
    }

    @Override
    public boolean isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, bookName, actionButtonString);
        String bookNameLoc = String.format(SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC, bookName, actionButtonString);
        return getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(actionButtonLoc, bookNameLoc).state().waitForDisplayed();

    }

    @Override
    public void openBookWithSpecificTypeAndSpecificNameAndSpecificActionButton(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, bookName, actionButtonString);
        String bookNameLoc = String.format(SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC, bookName, actionButtonString);
        IButton bookNameButton = getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(actionButtonLoc, bookNameLoc);
        bookNameButton.click();
    }
}
