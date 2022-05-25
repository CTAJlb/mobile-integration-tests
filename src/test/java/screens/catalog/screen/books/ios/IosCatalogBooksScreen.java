package screens.catalog.screen.books.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.Attributes;
import aquality.appium.mobile.elements.ElementType;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ScreenType(platform = PlatformName.IOS)
public class IosCatalogBooksScreen extends CatalogBooksScreen implements IWorkingWithListOfBooks {
    private static final String PROGRESS_BAR_BY_BOOK_NAME_LOC =
            "//XCUIElementTypeStaticText[@name=\"%s\"]/following-sibling::XCUIElementTypeProgressIndicator";
    private static final String ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            "//XCUIElementTypeStaticText[@name=\"%s\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[contains(@name,\"%s\")]";
    private static final String BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
    private static final String AUTHOR_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]";

    private static final String ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            "//XCUIElementTypeStaticText/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[contains(@name,\"%s\")]";
    private static final String BOOK_NAME_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
    private static final String AUTHOR_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]";
    private final ILabel lblNoResults = AqualityServices.getElementFactory().getLabel(
            By.xpath("//XCUIElementTypeStaticText[contains(@name, \"No results\")]"), "No results found");
    private static final String BOOKS_NAME_LOCATOR = "//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText[1]";
    private final ILabel lblNameOfFirstBook = AqualityServices.getElementFactory().getLabel(
            By.xpath("//XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[1]"), "Name of first book");

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
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RESERVE) {
            String bookNameForConditionalWait = bookNameForLocator;
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(bookNameForConditionalWait), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
            AqualityServices.getConditionalWait().waitFor(() -> !isDownloadingProgressComplete(bookNameForConditionalWait), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
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
    public boolean isDownloadingProgressComplete(String bookName) {
        return Objects.equals(getElementFactory().getLabel(By.xpath(String.format(PROGRESS_BAR_BY_BOOK_NAME_LOC, bookName)), "lblProgressBar").getAttribute(Attributes.VALUE), "90%");
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
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RESERVE) {
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(lblBookName.getAttribute(IosAttributes.NAME)), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }
        return bookInfo;
    }

    @Override
    public boolean isSearchResultsEmpty() {
        return lblNoResults.state().isDisplayed();
    }

    @Override
    public boolean isBooksContainWord(String word) {
        List<String> books = getBooksName();
        boolean isContain = true;
        for (String book: books) {
            if (!book.contains(word.toLowerCase())) {
                isContain = false;
                break;
            }
        }
        return isContain;
    }

    @Override
    public String getNameOfFirstBook() {
        return lblNameOfFirstBook.getText();
    }

    private List<String> getBooksName() {
        List<ILabel> lblBooks = getElementFactory().findElements(By.xpath(BOOKS_NAME_LOCATOR), ElementType.LABEL);
        List<String> booksName = new ArrayList<>();
        lblBooks.forEach(book->booksName.add(book.getText().toLowerCase()));


        for (String s: booksName
             ) {
            System.out.println("sdfsf: " + s);
        }


        return booksName;
    }
}
