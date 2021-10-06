package screens.catalog.screen.books.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.EnumBookType;
import constants.application.attributes.IosAttributes;
import constants.application.timeouts.BooksTimeouts;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.catalog.screen.books.CatalogBooksScreen;

import java.time.Duration;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosCatalogBooksScreen extends CatalogBooksScreen implements IWorkingWithListOfBooks {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeCollectionView";
    //
    private static final String ACTION_BUTTON_ON_BOOK_PATTERN = "//XCUIElementTypeButton[@name=\"%1$s\"]";
    private static final String BOOKS_LOC = ".//XCUIElementTypeCell";
    private static final String BOOK_BLOCK_BY_BUTTON_LOC = "//XCUIElementTypeCell[.//XCUIElementTypeButton[@name=\"%1$s\"]]";
    private static final String BOOK_TITLE_LOC = "//XCUIElementTypeStaticText[@name][1]";
    private static final String AUTHOR_NAME_XPATH_PATTERN =
            "//XCUIElementTypeStaticText[@name=\"%s\"]//following-sibling::XCUIElementTypeStaticText";
    private static final String ELEMENTS_TO_WAIT_FOR_XPATH = "//XCUIElementTypeCell//XCUIElementTypeOther//XCUIElementTypeStaticText";
    private static final int COUNT_OF_BUTTONS_TO_WAIT_FOR = 5;
    private static final String BUTTON_FOR_GIVEN_BOOK_XPATH_PATTERN =
            "//XCUIElementTypeStaticText[contains(@name,\"%1$s\")]//following-sibling::XCUIElementTypeOther//*[contains(@name,\"%2$s\")]";
    private static final String LIBRARY_BUTTON_LOCATOR_PATTERN = "//XCUIElementTypeButton[@name=\"%1$s\"]";
    private static final int MILLIS_TO_WAIT_FOR_SEARCH_LOADING = 40000;

    //
    private static final String SPECIFIC_BOOK_NAME_LOC = "//XCUIElementTypeStaticText[contains(@name,\"%1$s\")]";
    private static final String AUTHOR_FOR_BOOK_WITH_SPECIFIC_NAME_LOC = SPECIFIC_BOOK_NAME_LOC + "/following-sibling::XCUIElementTypeStaticText";
    private static final String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC = "//XCUIElementTypeStaticText[contains(@name,\"%s\")]/following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText[@name=\"%s\"]/parent::XCUIElementTypeButton";


    public IosCatalogBooksScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    private List<ILabel> getFoundBooks() {
        return getElementFactory().findElements(By.xpath(BOOKS_LOC), ElementType.LABEL);
    }

    @Override
    public int getFoundBooksCount() {
        return getFoundBooks().size();
    }

    @Override
    public CatalogBookModel getBookInfo(final String title) {
        return new CatalogBookModel()
                .setTitle(title)
                .setAuthor(getAuthorsName(title));
    }

    @Override
    public CatalogBookModel clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        IButton bookNameButton = getSpecificActionButtonForBookWithSpecificTypeAndSpecificNameFromListOfBooks(bookType, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_FOR_BOOK_WITH_SPECIFIC_NAME_LOC, bookName)), "lblAuthor");
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(lblAuthor.getAttribute(IosAttributes.NAME));
        bookNameButton.click();
        return bookInfo;
    }

    @Override
    public void clickBookByTitleButtonWithKey(String title, EnumActionButtonsForBooksAndAlertsKeys actionButton) {
        String key = actionButton.i18n();
        IButton bookAddBtn =
                getButtonForBookWithAction(title, key);
        clickOnSpecificBookElement(bookAddBtn);
    }

    @Override
    public CatalogBookModel openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        IButton bookNameButton = getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(bookType, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_FOR_BOOK_WITH_SPECIFIC_NAME_LOC, bookName)), "lblAuthor");
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(lblAuthor.getAttribute(IosAttributes.NAME));
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
        IButton button = getElementFactory().getButton(By.xpath(getBookAddButtonLocatorWithGivenType(actionButtonKey, bookType)), key);
        button.getTouchActions().scrollToElement(SwipeDirection.DOWN);

        String bookTitle =
                getElementFactory().getButton(By.xpath(getBookAddButtonLocatorWithGivenType(actionButtonKey, bookType)), key).getText();
        //testing fix for misclick
        switchToEbooksTab();
        waitForPageLoading();
        //ends here
        return openBook(button, bookTitle);
    }

    @Override
    public CatalogBookModel scrollToBookByNameAndClickActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName) {
        String key = actionButtonKey.i18n();
        IButton actionButton = getButtonForBookWithAction(bookName, key);
        switchToEbooksTab();
        if (!actionButton.state().waitForDisplayed()) {
            actionButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        AqualityServices.getLogger().info("Started Thread.sleep");
        try {
            Thread.sleep(MILLIS_TO_WAIT_FOR_SEARCH_LOADING);
        } catch (InterruptedException e) {
            AqualityServices.getLogger().debug("Thread.sleep issue happened. " + e.getLocalizedMessage());
        }
        AqualityServices.getLogger().info("Finished Thread.sleep");
        return openBook(actionButton, bookName);
    }

    @Override
    public CatalogBookModel scrollToBookByNameAndClickGetOrDownloadActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey1, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey2, String bookName) {
        //this does not use for ios
        return null;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public boolean isErrorButtonPresent() {
        return false;
    }

    private CatalogBookModel performActionOnBook(EnumActionButtonsForBooksAndAlertsKeys buttonName) {
        IButton button = getAddBookButton(buttonName);
        button.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        String bookTitle =
                getBookJacketWithGivenButtonName(buttonName).findChildElement(By.xpath(BOOK_TITLE_LOC), ElementType.LABEL).getText();
        return openBook(button, bookTitle);
    }

    private ILabel getBookJacketWithGivenButtonName(EnumActionButtonsForBooksAndAlertsKeys button) {
        String key = button.i18n();
        return getElementFactory().getLabel(By.xpath(String.format(BOOK_BLOCK_BY_BUTTON_LOC, key)), "Book jacket with button " + key);
    }

    private void clickOnSpecificBookElement(IElement bookWithSpecificAddBtn) {
        bookWithSpecificAddBtn.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        bookWithSpecificAddBtn.click();
    }

    private String getBookAddButtonLocatorWithGivenType(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType) {
        String key = actionButtonKey.i18n();
        return String.format("", bookType, key);
    }

    private IButton getAddBookButton(EnumActionButtonsForBooksAndAlertsKeys button) {
        String key = button.i18n();
        return getElementFactory().getButton(By.xpath(String.format(ACTION_BUTTON_ON_BOOK_PATTERN, key)), key);
    }

    private CatalogBookModel openBook(IButton button, String bookTitle) {
        CatalogBookModel bookInfo = getBookInfo(bookTitle);
        button.click();
        return bookInfo;
    }

    private IButton getButtonForBookWithAction(String title, String key) {
        return getElementFactory().getButton(By.xpath(String.format(BUTTON_FOR_GIVEN_BOOK_XPATH_PATTERN, title, key)), key);
    }

    private void waitForPageLoading() {
        AqualityServices.getConditionalWait().waitFor(() -> getElementFactory().findElements(By.xpath(ELEMENTS_TO_WAIT_FOR_XPATH), ElementType.BUTTON).size() >= COUNT_OF_BUTTONS_TO_WAIT_FOR);
    }

    private String getAuthorsName(String title) {
        ILabel label = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_NAME_XPATH_PATTERN, title)), title);
        return label.state().isDisplayed() ? label.getText() : "";
    }

    private void switchToEbooksTab() {
        getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_LOCATOR_PATTERN, "eBooks")), "eBooks").click();
    }
}
