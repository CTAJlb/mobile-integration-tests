package screens.bookDetails.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.attributes.IosAttributes;
import constants.application.timeouts.BooksTimeouts;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.junit.Assert;
import org.openqa.selenium.By;
import screens.bookDetails.BookDetailsScreen;

import java.time.Duration;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosBookDetailsScreen extends BookDetailsScreen {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeStaticText[@name=//XCUIElementTypeNavigationBar/@name]";
    private static final String BOOK_MAIN_INFO = "//XCUIElementTypeStaticText[@name=\"Description\"]//preceding-sibling::XCUIElementTypeStaticText[@name]";
    private static final String BOOK_ACTION_BUTTON_LOC = "//XCUIElementTypeButton/XCUIElementTypeStaticText[@name=\"%s\"]";
    private static final String LBL_BOOK_AUTHORS_INFO = String.format("(%1$s)[%%d]", BOOK_MAIN_INFO);
    private static final String LBL_AUTHOR_IN_RELATED_BOOKS = "//XCUIElementTypeTable//XCUIElementTypeStaticText[@name=\"%s\"]";
    private static final String LBL_LIST_OF_RELATED_BOOKS = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeButton";


    private final ILabel lblBookTitleInfo =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther//XCUIElementTypeStaticText[@name][1]"), "Book title");
    private final ILabel lblPublished =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name,\"Published\")]/following::XCUIElementTypeStaticText"), "lblPublished");
    private final ILabel lblPublisher =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name,\"Publisher\")]/following::XCUIElementTypeStaticText"), "lblPublisher");
    private final ILabel lblCategories =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name,\"Categor\")]/following::XCUIElementTypeStaticText"), "lblCategories");
    private final ILabel lblDistributor =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name,\"Distributed\")]/following::XCUIElementTypeStaticText"), "lblDistributor");
    private final ILabel lblProgressBar =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeProgressIndicator"), "lblProgressBar");
    private final IButton btnCloseBookDetailsOnlyIOSTab =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton/XCUIElementTypeStaticText[contains(@name, \"Close\")]"), "Close button");
    private final IButton lblErrorDetails =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeAlert//XCUIElementTypeStaticText"), "Error details");
    private final ILabel lblBookCover =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther//XCUIElementTypeImage[1]"), "Book cover");
    private final ILabel lblTextInDescription =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"Description\"]/following::XCUIElementTypeTextView"), "Info in description section");
    private final IButton btnMoreInDescription =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeStaticText[@name=\"Description\"]//following::XCUIElementTypeStaticText[@name=\"More…\"]"), "More btn in Description section");
    private final IButton btnMoreInRelatedBooks =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeTable//XCUIElementTypeButton[@name=\"More…\"]"), "More button in related books section");


    public IosBookDetailsScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    public List<ILabel> getBookMainInfo() {
        return getElementFactory().findElements(By.xpath(BOOK_MAIN_INFO), ElementType.LABEL);
    }

    @Override
    public CatalogBookModel getBookInfo() {
        Assert.assertTrue("Book info was not loaded", AqualityServices.getConditionalWait().waitFor(() ->
                        getBookMainInfo().size() > 0,
                Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_PAGE_LOADED.getTimeoutMillis())));
        return new CatalogBookModel()
                .setTitle(lblBookTitleInfo.getText())
                .setAuthor(getElementFactory().getLabel(By.xpath(String.format(LBL_BOOK_AUTHORS_INFO,
                        getBookMainInfo().size())), "Author").getText());
    }

    @Override
    public boolean isActionButtonDisplayed(EnumActionButtonsForBooksAndAlertsKeys key) {
        return getActionButton(key).state().waitForDisplayed();
    }

    @Override
    public void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKeys) {
        IButton actionButton = getActionButton(buttonKeys);
        actionButton.state().waitForDisplayed();
        actionButton.click();
        if (buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.GET || buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.REMOVE
                || buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.DELETE || buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.RETURN
                || buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.RESERVE) {
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }
    }

    @Override
    public String getErrorDetails() {
        if (isErrorButtonPresent()) {
            return lblErrorDetails.getText();
        }
        return "";
    }

    @Override
    public boolean isErrorButtonPresent() {
        return lblErrorDetails.state().isDisplayed();
    }

    @Override
    public boolean isProgressBarDisplayed() {
        return lblProgressBar.state().isDisplayed();
    }

    @Override
    public void openErrorDetails() {

    }

    @Override
    public void swipeError() {

    }

    @Override
    public String getPublishedInfo() {
        return lblPublished.getAttribute(IosAttributes.NAME);
    }

    @Override
    public String getPublisherInfo() {
        return lblPublisher.getAttribute(IosAttributes.NAME);
    }

    @Override
    public String getCategoryInfo() {
        return lblCategories.getAttribute(IosAttributes.NAME);
    }

    @Override
    public String getDistributorInfo() {
        return lblDistributor.getAttribute(IosAttributes.NAME);
    }

    @Override
    public void closeBookDetailsOnlyForIOSTabIfDisplayed() {
        if (btnCloseBookDetailsOnlyIOSTab.state().isDisplayed()) {
            btnCloseBookDetailsOnlyIOSTab.click();
        }
    }

    @Override
    public boolean isBookHasCover() {
        return lblBookCover.state().isExist();
    }

    @Override
    public boolean isDescriptionNotEmpty() {
        return !lblTextInDescription.getText().isEmpty();
    }

    @Override
    public boolean isMoreBtnInDescriptionAvailable() {
        return btnMoreInDescription.state().isClickable();
    }

    @Override
    public boolean isRelatedBooksExists(String authorName) {
        ILabel lblAuthorInRelatedBooks = getElementFactory().getLabel(By.xpath(String.format(LBL_AUTHOR_IN_RELATED_BOOKS, authorName)), "Author in related books section");
        return lblAuthorInRelatedBooks.state().isDisplayed();
    }

    @Override
    public boolean isListOfBooksDisplayed() {
        List<ILabel> listOfRelatedBooks = getElementFactory().findElements(By.xpath(LBL_LIST_OF_RELATED_BOOKS), ElementType.LABEL);
        return listOfRelatedBooks.size() != 0;
    }

    @Override
    public boolean isMoreBtnAvailableInRelatedBooks() {
        return btnMoreInRelatedBooks.state().isClickable();
    }

    @Override
    public void tapMoreBtnInRelatedBooks() {
        btnMoreInRelatedBooks.click();
    }

    private IButton getActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKey) {
        String key = buttonKey.i18n();
        return getElementFactory().getButton(By.xpath(String.format(BOOK_ACTION_BUTTON_LOC, key)), key);
    }
}
