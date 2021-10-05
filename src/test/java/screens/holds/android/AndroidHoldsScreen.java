package screens.holds.android;

import aquality.appium.mobile.application.PlatformName;
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
    private static final String SPECIFIC_BOOK_NAME_LOC = "//android.widget.TextView[contains(@text,\"%s\")]";

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
        return getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(EnumBookType.EBOOK, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC).state().waitForDisplayed();
    }

    @Override
    public void openBookWithSpecificTypeAndSpecificNameAndSpecificActionButton(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(EnumBookType.EBOOK, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC).click();
    }
}
