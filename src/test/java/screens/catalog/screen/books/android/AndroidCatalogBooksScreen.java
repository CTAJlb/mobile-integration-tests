package screens.catalog.screen.books.android;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.EnumBookType;
import constants.application.attributes.AndroidAttributes;
import constants.application.attributes.IosAttributes;
import constants.application.timeouts.BooksTimeouts;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.swipe.SwipeElementUtils;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.catalog.screen.books.CatalogBooksScreen;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidCatalogBooksScreen extends CatalogBooksScreen implements IWorkingWithListOfBooks {
    private static final String ADD_BOOK_BUTTON_PATTERN = "//android.widget.Button[@text=\"%1$s\"]";
    private static final String BOOKS_LOC = ".//*[contains(@resource-id,\"bookCellIdle\")]";
    private static final String BOOK_BLOCK_BY_TITLE_LOC =
            "//*[contains(@resource-id,\"bookCellIdle\") and .//*[contains(@resource-id,\"bookCellIdleTitle\") and contains(@text, \"%1$s\")]]";
    private static final String BOOK_TITLE_LOC = "//*[contains(@resource-id,\"bookCellIdleTitle\")]";
    private static final String BOOK_AUTHOR_LOC = "//*[contains(@resource-id,\"bookCellIdleAuthor\")]";
    private static final String BOOK_ADD_BUTTON_LOC =
            "//*[contains(@resource-id,\"bookCellIdleButtons\")]/android.widget.Button[@text=\"%1$s\"]";
    private static final String BOOK_OF_TYPE_BUTTON_PATTERN =
            "//android.widget.TextView[contains(@resource-id,\"bookCellIdleMeta\") and @text=\"%1$s\"]"
                    + "/following-sibling::android.widget.LinearLayout/android.widget.Button[@text=\"%2$s\"]";
    private static final String BOOK_BY_NAME_BUTTON_PATTERN =
            "//android.widget.TextView[contains(@resource-id,\"bookCellIdleTitle\") and @text=\"%1$s\"]/following-sibling::android.widget.LinearLayout/android.widget.Button[@text=\"%2$s\"]";
    private static final String BOOK_COVER_LOCATOR = "//*[contains(@resource-id,\"bookCellIdleCover\")]";
    private static final String LBL_IN_PROGRESS_TITLE = "//android.widget.TextView[contains(@resource-id,\"bookCellInProgressTitle\") and @text=\"%s\"]";
    private static final String BOOK_JACKET_XPATH_PATTERN =
            "//*[contains(@resource-id,\"bookCellIdle\") and .//android.widget.Button[@text=\"%1$s\"]]";
    private String RELATIVE_BOOK_TITLE_LOCATOR_PATTERN =
            "%s/../preceding-sibling::android.widget.TextView[contains(@resource-id,\"bookCellIdleTitle\")]";

    private final ILabel lblFirstFoundBook = getElementFactory().getLabel(By.xpath(BOOKS_LOC), "First found book");
    private final ILabel lblErrorDetails = getElementFactory().getLabel(By.id("errorDetails"), "Error details");
    private final IButton btnErrorDetails = getElementFactory().getButton(By.id("bookCellErrorButtonDetails"), "Error details");

    //
    private static final String SPECIFIC_BOOK_NAME_LOC = "//android.widget.TextView[contains(@text,\"%s\")]";
    private static final String AUTHOR_FOR_BOOK_WITH_SPECIFIC_NAME_LOC = SPECIFIC_BOOK_NAME_LOC + "//following-sibling::android.widget.TextView[contains(@resource-id,\"bookCellIdleAuthor\")]";
    private static final String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC = "//android.view.ViewGroup/android.widget.TextView[contains(@text,\"%s\")]/following-sibling::android.widget.LinearLayout//*[@text=\"%s\"]";


    public AndroidCatalogBooksScreen() {
        super(By.id("feedWithGroups"));
    }

    @Override
    public int getFoundBooksCount() {
        return getFoundBooks().size();
    }

    @Override
    public CatalogBookModel getBookInfo(final String title) {
        return getBookModel(getBlockLocator(title));
    }

    @Override
    public CatalogBookModel clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        IButton bookNameButton = getSpecificActionButtonForBookWithSpecificTypeAndSpecificNameFromListOfBooks(bookType, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_FOR_BOOK_WITH_SPECIFIC_NAME_LOC, bookName)), "lblAuthor");
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(lblAuthor.getText());
        bookNameButton.click();
        return bookInfo;
    }

    @Override
    public void clickBookByTitleButtonWithKey(String title, EnumActionButtonsForBooksAndAlertsKeys key) {
        String buttonName = key.i18n();
        IButton bookActionBtn =
                getElementFactory().getButton(By.xpath(getBlockLocator(title) + String.format(BOOK_ADD_BUTTON_LOC, buttonName)), buttonName);
        clickOnSpecificBookElement(bookActionBtn);
        AqualityServices.getConditionalWait().waitFor(() -> !bookActionBtn.state().isDisplayed(), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
    }

    @Override
    public CatalogBookModel openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        IButton bookNameButton = getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(bookType, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_FOR_BOOK_WITH_SPECIFIC_NAME_LOC, bookName)), "lblAuthor");
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(lblAuthor.getText());
        bookNameButton.click();
        return bookInfo;
    }

    @Override
    public boolean isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        return getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(EnumBookType.EBOOK, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC).state().waitForDisplayed();
    }

    @Override
    public CatalogBookModel scrollToBookByTypeAndClickActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType) {
        String key = actionButtonKey.i18n();
        String bookAddButtonLocator = getBookAddButtonLocatorWithGivenType(actionButtonKey, bookType);
        IButton button = getElementFactory().getButton(By.xpath(bookAddButtonLocator), key);
        button.getTouchActions().scrollToElement(SwipeDirection.DOWN);

        String bookTitle =
                getElementFactory().getButton(By.xpath(String.format(RELATIVE_BOOK_TITLE_LOCATOR_PATTERN, bookAddButtonLocator)), key).getText();
        return openBook(button, bookTitle);
    }

    @Override
    public CatalogBookModel scrollToBookByNameAndClickActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName) {
        String bookAddButtonLocator = getBookActionButtonLocatorWithGivenName(actionButtonKey, bookName);
        IButton button = getElementFactory().getButton(By.xpath(bookAddButtonLocator), actionButtonKey.i18n());
        button.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        return openBook(button, bookName);
    }

    @Override
    public CatalogBookModel scrollToBookByNameAndClickGetOrDownloadActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey1, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey2, String bookName) {
        String bookAddButtonLocator1 = getBookActionButtonLocatorWithGivenName(actionButtonKey1, bookName);
        String bookAddButtonLocator2 = getBookActionButtonLocatorWithGivenName(actionButtonKey2, bookName);
        IButton button1 = getElementFactory().getButton(By.xpath(bookAddButtonLocator1), actionButtonKey1.i18n());
        IButton button2 = getElementFactory().getButton(By.xpath(bookAddButtonLocator2), actionButtonKey2.i18n());
        IButton buttonForClick = null;
        if (button1.state().waitForDisplayed()) {
            buttonForClick = button1;
            button1.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        } else if (button2.state().waitForDisplayed()) {
            button2.getTouchActions().scrollToElement(SwipeDirection.DOWN);
            buttonForClick = button2;
        }
        return openBook(buttonForClick, bookName);
    }

    @Override
    public String getErrorMessage() {
        if (isErrorButtonPresent()) {
            btnErrorDetails.click();
            lblErrorDetails.state().waitForDisplayed();
            return lblErrorDetails.getText();
        }
        AqualityServices.getLogger().info("Error details button is not present");
        return "";
    }

    @Override
    public boolean isErrorButtonPresent() {
        return btnErrorDetails.state().isDisplayed();
    }

    private CatalogBookModel performActionOnBook(EnumActionButtonsForBooksAndAlertsKeys buttonName) {
        SwipeElementUtils.swipeElementDown(lblFirstFoundBook);
        IButton button = getAddBookButton(buttonName);
        button.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        String bookTitle =
                getBookJacketWithGivenButtonLabel(buttonName).findChildElement(By.xpath(BOOK_TITLE_LOC), ElementType.LABEL).getText();
        return openBook(button, bookTitle);
    }

    private ILabel getBookJacketWithGivenButtonLabel(EnumActionButtonsForBooksAndAlertsKeys button) {
        String key = button.i18n();
        return getElementFactory().getLabel(By.xpath(String.format(BOOK_JACKET_XPATH_PATTERN, key)), "Book jacket with " + key);
    }

    private void clickOnSpecificBookElement(IElement bookWithSpecificAddBtn) {
        if (bookWithSpecificAddBtn.state().waitForDisplayed()) {
            bookWithSpecificAddBtn.click();
        } else {
            bookWithSpecificAddBtn.getTouchActions().scrollToElement(SwipeDirection.DOWN);
            bookWithSpecificAddBtn.click();
        }
    }

    private IButton getAddBookButton(EnumActionButtonsForBooksAndAlertsKeys button) {
        String key = button.i18n();
        return getElementFactory().getButton(By.xpath(String.format(ADD_BOOK_BUTTON_PATTERN, key)), key);
    }

    private String getBookAddButtonLocatorWithGivenType(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType) {
        String key = actionButtonKey.i18n();
        return String.format(BOOK_OF_TYPE_BUTTON_PATTERN, bookType, key);
    }

    private String getBookActionButtonLocatorWithGivenName(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName) {
        String key = actionButtonKey.i18n();
        return String.format(BOOK_BY_NAME_BUTTON_PATTERN, bookName, key);
    }

    private CatalogBookModel openBook(IButton button, String bookTitle) {
        CatalogBookModel androidCatalogBookModel = getBookInfo(bookTitle);
        button.click();
        ILabel label = getElementFactory().getLabel(By.xpath(String.format(LBL_IN_PROGRESS_TITLE, bookTitle)), "lblInProgressTitle");
        if (label.state().waitForDisplayed()) {
            label.state().waitForNotDisplayed(Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }

        return androidCatalogBookModel;
    }

    private List<ILabel> getFoundBooks() {
        return getElementFactory().findElements(By.xpath(BOOK_COVER_LOCATOR), ElementType.LABEL);
    }

    private CatalogBookModel getBookModel(String mainLocator) {
        AqualityServices.getConditionalWait().waitFor(() -> getBookDescriptionFromImage(mainLocator) != null);
        return new CatalogBookModel()
                .setTitle(getBookParameter(mainLocator, BOOK_TITLE_LOC, "Book title"))
                .setAuthor(getBookParameter(mainLocator, BOOK_AUTHOR_LOC, "Book author"));
    }

    private String getBookDescriptionFromImage(String mainLocator) {
        return getElementFactory().getLabel(By.xpath(mainLocator + BOOK_COVER_LOCATOR), "Book image content description").getAttribute(AndroidAttributes.CONTENT_DESC);
    }

    private String getBookParameter(String mainLocator, String subLocator, String name) {
        return Objects.requireNonNull(getElementFactory().getLabel(By.xpath(mainLocator + subLocator), name).getText());
    }

    private String getBlockLocator(String title) {
        return String.format(BOOK_BLOCK_BY_TITLE_LOC, title);
    }
}
