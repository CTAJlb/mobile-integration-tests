package screens.holds.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.holds.HoldsScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidHoldsScreen extends HoldsScreen implements IWorkingWithListOfBooks {
    private static final String ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC = "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.LinearLayout//*[@text=\"%s\"]";
    private static final String BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC = ACTION_BUTTON_BY_BOOK_NAME_AND_BUTTON_NAME_LOC + "/ancestor::android.view.ViewGroup/android.widget.TextView[1]";
    private static final String BOOK_LOC = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout";
    private static final String BOOK_TITLE = BOOK_LOC + "/android.view.ViewGroup/android.widget.TextView[1]";
    private static final String BOOK_AUTHOR = BOOK_LOC + "/android.view.ViewGroup/android.widget.TextView[2]";

    private final ILabel lblNoBooks = getElementFactory().getLabel(By.id("feedEmptyText"), "No Books Present");
    private final ILabel lblHolds = getElementFactory().getLabel(
            By.xpath("//android.view.ViewGroup[contains(@resource-id,\"mainToolbar\")]/android.widget.TextView"), "Reservations");

    public AndroidHoldsScreen() {
        super(By.xpath("//android.widget.TextView[@text=\"Holds\"]"));
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
    public void openBook(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(BOOK_NAME_BY_BOOK_NAME_AND_BUTTON_NAME_LOC, bookName, actionButtonString);
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
}
