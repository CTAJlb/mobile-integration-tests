package screens.catalog.screen.books.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.EnumBookType;
import enums.timeouts.BooksTimeouts;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.catalog.screen.books.CatalogBooksScreen;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidCatalogBooksScreen extends CatalogBooksScreen implements IWorkingWithListOfBooks {
    private static final String PROGRESS_BAR_BY_BOOK_NAME_LOC =
            "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.ProgressBar";
    private static final String ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.LinearLayout//*[@text=\"%s\"]";
    private static final String BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::android.view.ViewGroup/android.widget.TextView[1]";
    private static final String AUTHOR_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::android.view.ViewGroup/android.widget.TextView[2]";

    private static final String ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            "//android.widget.TextView/following-sibling::android.widget.LinearLayout//*[@text=\"%s\"]";
    private static final String BOOK_NAME_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::android.view.ViewGroup/android.widget.TextView[1]";
    private static final String AUTHOR_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC =
            ACTION_BUTTON_ON_THE_FIRST_BOOK_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::android.view.ViewGroup/android.widget.TextView[2]";
    private static final String BOOKS_NAME_LOCATOR =
            "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[1]";
    private final ILabel lblNameOfFirstBook = AqualityServices.getElementFactory().getLabel(
            By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[1]"), "Name of first book");
    private final ILabel lblNoResults = AqualityServices.getElementFactory().getLabel(
            By.xpath("//android.widget.TextView[contains(@text, \"No results\")]"), "No results found");

    public AndroidCatalogBooksScreen() {
        super(By.id("feedWithGroups"));
    }

    @Override
    public CatalogBookModel clickActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString);
        IButton actionButton = getActionButtonFromListOfBooks(actionButtonLoc);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString)), "lblAuthor");
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
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RESERVE)
        {
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(bookName), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }
        return bookInfo;
    }

    @Override
    public CatalogBookModel openBookAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString);
        ILabel lblBookName = getBookNameLabelFromListOfBooks(bookNameLoc);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString)), "lblAuthor");
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
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString);
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
            author = lblAuthor.getText();
        }
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(lblBookName.getText())
                .setAuthor(author);
        actionButton.click();
        if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.GET || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RESERVE) {
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(lblBookName.getText()), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }
        return bookInfo;
    }

    @Override
    public void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookName) {
        String actionButtonString = actionButtonKey.i18n();
        getActionButtonFromListOfBooks(String.format(ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString)).click();
        if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.GET || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.REMOVE
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.DELETE || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RETURN
                || actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RESERVE)
        {
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(bookName), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }
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

    @Override
    public boolean isActionButtonDisplayed(String bookName, EnumActionButtonsForBooksAndAlertsKeys key) {
        return getActionButton(bookName, key).state().isDisplayed();
    }

    private List<String> getBooksName() {
        List<ILabel> lblBooks = getElementFactory().findElements(By.id(BOOKS_NAME_LOCATOR), ElementType.LABEL);
        List<String> booksName = new ArrayList<>();
        lblBooks.forEach(book->booksName.add(book.getText().toLowerCase()));
        return booksName;
    }

    private IButton getActionButton(String bookName, EnumActionButtonsForBooksAndAlertsKeys buttonKey) {
        String key = buttonKey.i18n();
        return getElementFactory().getButton(By.xpath(String.format(ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, key)), key);
    }
}
