package screens.books.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.swipe.SwipeElementUtils;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.books.BooksScreen;

import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosBooksScreen extends BooksScreen {
    private static final String MAIN_ELEMENT_LOC = "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[@name=\"My Books\"]";
    private static final String SPECIFIC_BOOK_NAME_LOC = "//XCUIElementTypeStaticText[contains(@name,\"%1$s\")]";
    private static final String BOOKS_LOC = "//XCUIElementTypeCollectionView//XCUIElementTypeCell";
    private static final String SPECIFIC_ACTION_BUTTON_ON_SPECIFIC_BOOK_LOC = "//XCUIElementTypeStaticText[contains(@name,\"%s\")]/following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText[@name=\"%s\"]/parent::XCUIElementTypeButton";
    private static final int MILLIS_TO_WAIT_FOR_SEARCH_LOADING = 40000;

    private ILabel mainBooksElementCollection = getElementFactory().getLabel(
            By.xpath("//XCUIElementTypeCollectionView"), "Elements collection container");
    private ILabel lblNoBooks =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name,'Visit the Catalog')]"), "No Books Present");

    public IosBooksScreen() {
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

    private void addWait(){
        try {
            Thread.sleep(MILLIS_TO_WAIT_FOR_SEARCH_LOADING);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
