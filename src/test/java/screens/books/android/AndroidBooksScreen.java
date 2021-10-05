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
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.books.BooksScreen;

import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBooksScreen extends BooksScreen implements IWorkingWithListOfBooks {
    private static final String MAIN_ELEMENT_LOC = "//android.widget.TextView[@text=\"Books\"]";
    private static final String BOOKS_LOC = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout";
    private static final String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC = "//android.view.ViewGroup/android.widget.TextView[contains(@text,\"%s\")]/following-sibling::android.widget.LinearLayout//*[@text=\"%s\"]";
    private static final String SPECIFIC_BOOK_NAME_LOC = "//android.widget.TextView[contains(@text,\"%s\")]";

    private ILabel lblNoBooks = getElementFactory().getLabel(By.id("feedEmptyText"), "No Books Present");
    private IButton btnMenuForRefresh =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"),
                    "Button Menu For Refresh");
    private IButton btnRefresh = getElementFactory().getButton(By.id("title"), "Refresh Button");

    public AndroidBooksScreen() {
        super(By.xpath(MAIN_ELEMENT_LOC));
    }

    @Override
    public boolean isNoBooksMessagePresent() {
        return lblNoBooks.state().waitForDisplayed();
    }

    @Override
    public boolean isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        addWait();
        return getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(EnumBookType.EBOOK, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC).state().waitForDisplayed();
    }

    @Override
    public int getCountOfBooks() {
        return getListOfBooks().size();
    }

    @Override
    public void refreshList() {
        btnMenuForRefresh.click();
        btnRefresh.click();
    }

    private void addWait() {
        AqualityServices.getConditionalWait().waitFor(() -> isNoBooksMessagePresent() && getCountOfBooks() > 0);
    }

    @Override
    public void openBookWithSpecificTypeAndSpecificNameAndSpecificActionButton(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        addWait();
        getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(EnumBookType.EBOOK, bookName, actionButtonKey, SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, SPECIFIC_BOOK_NAME_LOC).click();
    }

    private List<IElement> getListOfBooks() {
        return getElementFactory().findElements(By.xpath(BOOKS_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
