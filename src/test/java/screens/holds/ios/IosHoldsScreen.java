package screens.holds.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.timeouts.BooksTimeouts;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;
import screens.holds.HoldsScreen;

import java.time.Duration;

@ScreenType(platform = PlatformName.IOS)
public class IosHoldsScreen extends HoldsScreen {
    private static final String MAIN_ELEMENT_EXISTING_BOOKS_IN_HOLDS = "//XCUIElementTypeStaticText[@name=\"WAITING FOR AVAILABILITY\"]";
    private static final String LBL_NO_BOOKS_LOC =
            "//XCUIElementTypeStaticText[@name=\"When you reserve a book from the catalog, it will show up here. Look here from time to time to see if your book is available to download.\"]";
    private static final String BOOK_INFO_LOCATOR_PATTERN = "//XCUIElementTypeStaticText[contains(@name,\"%1$s\")]";
    private static final String BOOK_BLOCK_BY_TITLE_LOC =
            String.format("//XCUIElementTypeCollectionView//XCUIElementTypeCell[.%1$s]", BOOK_INFO_LOCATOR_PATTERN);
    private static final String BOOK_ADD_BUTTON_LOC = "//XCUIElementTypeStaticText[@name=\"%1$s\"]";

    private final ILabel lblNoBooks = getElementFactory().getLabel(By.xpath(LBL_NO_BOOKS_LOC),
            "No Books Present");

    public IosHoldsScreen() {
        super(By.xpath(MAIN_ELEMENT_EXISTING_BOOKS_IN_HOLDS + "|" + LBL_NO_BOOKS_LOC));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().isDisplayed();
    }

    @Override
    public boolean isBookPresent(String bookTitle) {
        return getBook(bookTitle).state().waitForDisplayed();
    }

    @Override
    public boolean isBookNotPresent(String bookInfo) {
        return getBook(bookInfo).state().waitForNotDisplayed();
    }

    @Override
    public void performActionOnBook(String title, EnumActionButtonsForBooksAndAlertsKeys key) {
        clickOnSpecificBookElement(getBookActionButton(key, getBookBlockLocator(title)));
    }

    @Override
    public boolean isActionButtonPresentOnBook(String bookTitle, EnumActionButtonsForBooksAndAlertsKeys key) {
        IButton btnBookAdd = getBookActionButton(key, getBookBlockLocator(bookTitle));
        return btnBookAdd.state().waitForDisplayed(Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
    }

    private void clickOnSpecificBookElement(IElement bookWithSpecificAddBtn) {
        bookWithSpecificAddBtn.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        bookWithSpecificAddBtn.click();
    }

    private ILabel getBook(String bookInfo) {
        return getElementFactory().getLabel(By.xpath(String.format(BOOK_INFO_LOCATOR_PATTERN, bookInfo)), bookInfo);
    }

    private IButton getBookActionButton(EnumActionButtonsForBooksAndAlertsKeys key, String blockLoc) {
        return getElementFactory().getButton(By.xpath(blockLoc + String.format(BOOK_ADD_BUTTON_LOC, key.i18n())), String.format("Book %1$s", key.i18n()));
    }

    private String getBookBlockLocator(String title) {
        return String.format(BOOK_BLOCK_BY_TITLE_LOC, title);
    }
}
