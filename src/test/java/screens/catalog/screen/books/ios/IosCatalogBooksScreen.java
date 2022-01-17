package screens.catalog.screen.books.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
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

@ScreenType(platform = PlatformName.IOS)
public class IosCatalogBooksScreen extends CatalogBooksScreen implements IWorkingWithListOfBooks {
    private static final String PROGRESS_BAR_BY_BOOK_NAME_LOC =
            "//XCUIElementTypeStaticText[@name=\"%s\"]/following-sibling::XCUIElementTypeProgressIndicator";
    private static final String ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            "//XCUIElementTypeStaticText[@name=\"%s\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[@name=\"%s\"]";
    private static final String BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
    private static final String AUTHOR_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]";

    private static final String ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            "//XCUIElementTypeStaticText/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[@name=\"%s\"]";
    private static final String BOOK_NAME_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
    private static final String AUTHOR_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]";

    public IosCatalogBooksScreen() {
        super(By.xpath("//XCUIElementTypeCollectionView"));
    }

    @Override
    public CatalogBookModel clickActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String bookNameForLocator = bookName;
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookNameForLocator = bookNameForLocator + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookNameForLocator, actionButtonString);
        IButton actionButton = getActionButtonFromListOfBooks(actionButtonLoc);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookNameForLocator, actionButtonString)), "lblAuthor");
        String author = "";
        if (!lblAuthor.state().isDisplayed()) {
            author = null;
        } else {
            author = lblAuthor.getText();
        }
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(author);
        actionButton.click();
        if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.GET || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN) {
            String bookNameForConditionalWait = bookNameForLocator;
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(bookNameForConditionalWait), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }
        return bookInfo;
    }

    @Override
    public CatalogBookModel openBookAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String bookNameForLocator = bookName;
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookNameForLocator = bookNameForLocator + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookNameForLocator, actionButtonString);
        ILabel lblBookName = getBookNameLabelFromListOfBooks(bookNameLoc);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookNameForLocator, actionButtonString)), "lblAuthor");
        String author = "";
        if (!lblAuthor.state().isDisplayed()) {
            author = null;
        } else {
            author = lblAuthor.getText();
        }
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(author);
        lblBookName.click();
        return bookInfo;
    }

    @Override
    public boolean isBookDisplayed(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String bookNameForLocator = bookName;
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookNameForLocator = bookNameForLocator + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookNameForLocator, actionButtonString);
        return getBookNameLabelFromListOfBooks(bookNameLoc).state().isDisplayed();
    }

    @Override
    public boolean isProgressBarDisplayed(String bookName) {
        return getElementFactory().getLabel(By.xpath(String.format(PROGRESS_BAR_BY_BOOK_NAME_LOC, bookName)), "lblProgressBar").state().isDisplayed();
    }

    @Override
    public CatalogBookModel clickActionButtonOnTheFirstBookAndGetBookInfo(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, actionButtonString);
        IButton actionButton = getActionButtonFromListOfBooks(actionButtonLoc);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, actionButtonString)), "lblAuthor");
        ILabel lblBookName = getElementFactory().getLabel(By.xpath(String.format(BOOK_NAME_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, actionButtonString)), "lblBookName");
        String author = "";
        if (!lblAuthor.state().isDisplayed()) {
            author = null;
        } else {
            author = lblAuthor.getAttribute(IosAttributes.NAME);
        }
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(lblBookName.getAttribute(IosAttributes.NAME))
                .setAuthor(author);
        actionButton.click();
        if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.GET || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN) {
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(lblBookName.getAttribute(IosAttributes.NAME)), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }
        return bookInfo;
    }
}
