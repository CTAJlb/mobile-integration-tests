package screens.books.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.books.BooksScreen;

import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBooksScreen extends BooksScreen implements IWorkingWithListOfBooks {
    private static final String MAIN_ELEMENT_LOC = "//android.widget.TextView[@text=\"Books\"]";
    private static final String BOOKS_LOC = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout";
    private static final String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC = "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.LinearLayout//*[@text=\"%s\"]";
    private static final String SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC = SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC + "/ancestor::android.view.ViewGroup/android.widget.TextView[1]";

    private ILabel mainBooksElementCollection = getElementFactory().getLabel(
            By.xpath("//android.view.ViewGroup[contains(@resource-id,\"feedWithoutGroupsRefresh\")]"), "Elements collection container");
    private ILabel lblNoBooks = getElementFactory().getLabel(By.id("feedEmptyText"), "No Books Present");

    public AndroidBooksScreen() {
        super(By.xpath(MAIN_ELEMENT_LOC));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().waitForDisplayed();
    }

    @Override
    public boolean isBookPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC, bookName, actionButtonString);
        return getBookNameButtonFromListOfBooks(bookNameLoc).state().waitForDisplayed();
    }

    @Override
    public int getCountOfBooks() {
        return getListOfBooks().size();
    }

    @Override
    public void refreshList() {
        SwipeElementUtils.swipeElementDown(mainBooksElementCollection);
    }

    private void addWait() {
        AqualityServices.getConditionalWait().waitFor(() -> isNoBooksMessagePresent() && getCountOfBooks() > 0);
    }

    @Override
    public void openBook(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
        String bookNameLoc = String.format(SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC, bookName, actionButtonString);
        IButton bookNameButton = getBookNameButtonFromListOfBooks(bookNameLoc);
        bookNameButton.click();
    }

    private List<IElement> getListOfBooks() {
        return getElementFactory().findElements(By.xpath(BOOKS_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
