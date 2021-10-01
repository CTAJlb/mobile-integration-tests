package screens.holds.android;

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

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidHoldsScreen extends HoldsScreen {
    private final ILabel lblNoBooks = getElementFactory().getLabel(By.id("feedEmptyText"), "No Books Present");
    private final String BOOK_TITLE_LOCATOR_PATTERN = "//android.widget.TextView[contains(@resource-id,\"bookCellIdleTitle\") and contains(@text, \"%s\")]";

    private static final String BOOK_BLOCK_BY_TITLE_LOC =
            "//*[contains(@resource-id,\"bookCellIdle\") and .//*[contains(@resource-id,\"bookCellIdleTitle\") and contains(@text, \"%1$s\")]]";

    private static final String BOOK_ADD_BUTTON_LOC =
            "//*[contains(@resource-id,\"bookCellIdleButtons\")]/android.widget.Button[@text=\"%1$s\"]";

    public AndroidHoldsScreen() {
        super(By.xpath("//android.widget.TextView[contains(@text,\"Holds\")]"));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().waitForDisplayed();
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
        clickOnSpecificBookElement(getActionButton(key, getBookTitleLocator(title)));
    }

    @Override
    public boolean isActionButtonPresentOnBook(String bookTitle, EnumActionButtonsForBooksAndAlertsKeys key) {
        IButton btnBookAction = getActionButton(key, getBookTitleLocator(bookTitle));
        return btnBookAction.state().waitForDisplayed(Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
    }

    private void clickOnSpecificBookElement(IElement bookWithSpecificAddBtn) {
        bookWithSpecificAddBtn.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        bookWithSpecificAddBtn.click();
    }

    private ILabel getBook(String bookTitle) {
        ILabel book =
                getElementFactory().getLabel(By.xpath(String.format(BOOK_TITLE_LOCATOR_PATTERN, bookTitle)), "bookTitle");
        book.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        return book;
    }

    private IButton getActionButton(EnumActionButtonsForBooksAndAlertsKeys key, String blockLoc) {
        String buttonName = key.i18n();
        return getElementFactory().getButton(By.xpath(blockLoc + String.format(BOOK_ADD_BUTTON_LOC, buttonName)), buttonName);
    }

    private String getBookTitleLocator(String title) {
        return String.format(BOOK_BLOCK_BY_TITLE_LOC, title);
    }
}
