package screens.holds.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.holds.HoldsScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosHoldsScreen extends HoldsScreen implements IWorkingWithListOfBooks {
    private static final String MAIN_ELEMENT_LOC = "//XCUIElementTypeStaticText[@name=\"Reservations\"]";
    private static final String SPECIFIC_BOOK_NAME_LOC = "//XCUIElementTypeStaticText[contains(@name,\"%1$s\")]";
    private static final String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC = "//XCUIElementTypeStaticText[contains(@name,\"%s\")]/following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText[@name=\"%s\"]/parent::XCUIElementTypeButton";

    private final ILabel lblNoBooks = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"When you reserve a book from the catalog, it will show up here. Look here from time to time to see if your book is available to download.\"]"),
            "No Books Present");

    public IosHoldsScreen() {
        super(By.xpath(MAIN_ELEMENT_LOC));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().isDisplayed();
    }

    @Override
    public boolean isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        return getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(EnumBookType.EBOOK, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC).state().waitForDisplayed();
    }

    @Override
    public void openBookWithSpecificTypeAndSpecificNameAndSpecificActionButton(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(bookType, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC).click();
    }
}
