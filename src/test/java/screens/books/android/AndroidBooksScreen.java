package screens.books.android;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import constants.application.attributes.AndroidAttributes;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.swipe.SwipeElementUtils;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.books.BooksScreen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBooksScreen extends BooksScreen {
    private static final String MAIN_ELEMENT_LOC = "//android.widget.TextView[@text=\"Books\"]";
    private static final String BOOKS_LOC = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout";
    private static final String SPECIFIC_ACTION_BUTTON_ON_SPECIFIC_BOOK_LOC = "//android.widget.TextView[contains(@text,\"%s\")]/following-sibling::android.widget.LinearLayout/android.widget.Button[@text=\"%s\"]";
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
    public boolean isSpecificBookWithSpecificActionButtonPresent(CatalogBookModel bookInfo, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        addWait();
        String actionButton = actionButtonKey.i18n();
        IButton actionButtonOnBook = getElementFactory()
                .getButton(By.xpath(String.format(SPECIFIC_ACTION_BUTTON_ON_SPECIFIC_BOOK_LOC, bookInfo.getTitle(), actionButton)),
                        "Book with Action Button");
        if (!actionButtonOnBook.state().waitForDisplayed()) {
            actionButtonOnBook.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        return actionButtonOnBook.state().waitForDisplayed();
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

    private void addWait(){
        AqualityServices.getConditionalWait().waitFor(() -> isNoBooksMessagePresent() && getCountOfBooks() > 0);
    }

    @Override
    public void openBookWithDefiniteNameAndDefiniteActionButton(CatalogBookModel bookInfo, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        addWait();
        String bookName = bookInfo.getTitle();
        String actionButtonString = actionButtonKey.i18n();
        IButton actionButton = getElementFactory().getButton(By.xpath(String.format(SPECIFIC_ACTION_BUTTON_ON_SPECIFIC_BOOK_LOC, bookName, actionButtonString)), "Action Button");
        if (!actionButton.state().waitForDisplayed()){
            actionButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        if (actionButton.state().isDisplayed()) {
            getElementFactory().getButton(By.xpath(String.format(SPECIFIC_BOOK_NAME_LOC, bookName)), bookName).click();
        } else {
            throw new RuntimeException("There is not book with action button and title-" + bookName);
        }
    }

    private List<IElement> getListOfBooks() {
        return getElementFactory().findElements(By.xpath(BOOKS_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
