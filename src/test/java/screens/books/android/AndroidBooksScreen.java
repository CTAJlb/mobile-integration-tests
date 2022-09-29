package screens.books.android;

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

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBooksScreen extends BooksScreen implements IWorkingWithListOfBooks {
    private static final String BOOKS_LOC = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout";
    private static final String BOOK_NAME_LOC = BOOKS_LOC + "/android.view.ViewGroup/android.widget.TextView[1]";
    private static final String AUTHORS_NAME_LOC = BOOKS_LOC + "/android.view.ViewGroup/android.widget.TextView[2]";
    private static final String ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC = "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.LinearLayout//*[@text=\"%s\"]";
    private static final String BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC = ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::android.view.ViewGroup/android.widget.TextView[1]";

    private final ILabel mainBooksElementCollection = getElementFactory().getLabel(
            By.xpath("//android.view.ViewGroup[contains(@resource-id,\"feedContentRefresh\")]"), "Elements collection container");
    private final ILabel lblNoBooks = getElementFactory().getLabel(By.id("feedEmptyText"), "No Books Present");
    private final ILabel lblMyBooks = getElementFactory().getLabel(
            By.xpath("//android.view.ViewGroup[contains(@resource-id,\"mainToolbar\")]/android.widget.TextView"), "My Books");

    public AndroidBooksScreen() {
        super(By.xpath("//android.widget.TextView[@text=\"Books\"]"));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().waitForDisplayed();
    }

    @Override
    public boolean isBookDisplayed(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
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
        String actionButtonString = actionButtonKey.i18n();
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
