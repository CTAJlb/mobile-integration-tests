package screens.books.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import enums.EnumBookType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.books.BooksScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosBooksScreen extends BooksScreen implements IWorkingWithListOfBooks {
    private static final String BOOKS_LOC = "//XCUIElementTypeCollectionView//XCUIElementTypeCell";
    private static final String ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC = "//XCUIElementTypeStaticText[@name=\"%s\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[contains(@name,\"%s\")]";
    private static final String BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC = ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
    private static final String BOOK_NAME_LOC = BOOKS_LOC + "/XCUIElementTypeOther/XCUIElementTypeStaticText[1]";
    private static final String AUTHORS_NAME_LOC = BOOKS_LOC + "/XCUIElementTypeOther/XCUIElementTypeStaticText[2]";

    private final ILabel mainBooksElementCollection = getElementFactory().getLabel(
            By.xpath("//XCUIElementTypeCollectionView"), "Elements collection container");
    private final ILabel lblNoBooks =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name,'Visit the Catalog')]"), "No Books Present");
    private final ILabel lblMyBooks = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"My Books\"]"), "My books");

    public IosBooksScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[@name=\"My Books\"]"));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().waitForDisplayed();
    }

    @Override
    public boolean isBookDisplayed(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.getDefaultLocalizedValue();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString);
        return getBookNameLabelFromListOfBooks(bookNameLoc).state().waitForDisplayed();
    }

    @Override
    public int getCountOfBooks() {
        return getListOfBooks().size();
    }

    @Override
    public void refreshList() {
        SwipeElementUtils.swipeElementDown(mainBooksElementCollection);
    }

    @Override
    public void openBook(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.getDefaultLocalizedValue();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString);
        ILabel lblBookName = getBookNameLabelFromListOfBooks(bookNameLoc);
        lblBookName.click();
    }

    @Override
    public boolean isBooksScreenOpened() {
        return lblMyBooks.state().isDisplayed();
    }

    @Override
    public boolean areBooksDisplayed(List<String> listOfBooks) {
        boolean isDisplayed = true;
        List<String> listOfBooksNames = getNamesOfBooks();
        for (String book: listOfBooksNames) {
            if(!listOfBooks.contains(book)) {
                isDisplayed = false;
                break;
            }
        }
        return isDisplayed;
    }

    @Override
    public List<String> getListOfAuthors() {
        List<IElement> listOfBooks = getElementFactory().findElements(By.xpath(AUTHORS_NAME_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
        List<String> authorsNames = new ArrayList<>();
        listOfBooks.forEach(book -> authorsNames.add(book.getText()));
        return authorsNames;
    }

    @Override
    public List<String> getListOfTitles() {
        return getNamesOfBooks();
    }

    private List<String> getNamesOfBooks() {
        List<String> bookNames = new ArrayList<>();
        List<IElement> listOfBooks = getElementFactory().findElements(By.xpath(BOOK_NAME_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
        listOfBooks.forEach(book -> bookNames.add(book.getText()));
        return bookNames;
    }

    private List<IElement> getListOfBooks() {
        return getElementFactory().findElements(By.xpath(BOOKS_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
