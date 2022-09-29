package screens.holds.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import enums.EnumBookType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.holds.HoldsScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosHoldsScreen extends HoldsScreen implements IWorkingWithListOfBooks {
    private static final String ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC = "//XCUIElementTypeStaticText[@name=\"%s\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[contains(@name,\"%s\")]";
    private static final String BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC = ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
    private static final String BOOK_AUTHOR = "//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText[2]";
    private static final String BOOK_TITLE = "//XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText[1]";

    private final ILabel lblNoBooks = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"When you reserve a book from the catalog, it will show up here. Look here from time to time to see if your book is available to download.\"]"),
            "No Books Present");
    private final ILabel lblHolds = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"Reservations\"]"), "Reservations");

    public IosHoldsScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Reservations\"]"));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().isDisplayed();
    }

    @Override
    public boolean isBookDisplayed(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String bookNameForLocator = bookName;
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookNameForLocator = bookNameForLocator + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookNameForLocator, actionButtonString);
        return getBookNameLabelFromListOfBooks(bookNameLoc).state().waitForDisplayed();
    }

    @Override
    public void openBook(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String bookNameForLocator = bookName;
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookNameForLocator = bookNameForLocator + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookNameForLocator, actionButtonString);
        ILabel lblBookName = getBookNameLabelFromListOfBooks(bookNameLoc);
        lblBookName.click();
    }

    @Override
    public boolean isHoldsScreenOpened() {
        return lblHolds.state().isDisplayed();
    }

    @Override
    public List<String> getListOfAuthors() {
        List<String> bookAuthors = new ArrayList<>();
        List<IElement> listOfBooks = getElementFactory().findElements(By.xpath(BOOK_AUTHOR), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
        listOfBooks.forEach(book -> bookAuthors.add(book.getText()));
        return bookAuthors;
    }

    @Override
    public List<String> getListOfTitles() {
        List<String> bookNames = new ArrayList<>();
        List<IElement> listOfBooks = getElementFactory().findElements(By.xpath(BOOK_TITLE), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
        listOfBooks.forEach(book -> bookNames.add(book.getText()));
        return bookNames;
    }

    @Override
    public String getNameOfSorting() {
        //only for Android
        return null;
    }

    @Override
    public void sortBy() {
        //only for Android
    }
}
