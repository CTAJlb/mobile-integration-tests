package screens.bookDetails.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
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
    private static final String BOOK_ACTION_BUTTON_LOC = "//XCUIElementTypeButton[@name=\"%s\"]";
    private static final String LBL_BOOK_AUTHORS_INFO = String.format("(%1$s)[%%d]", BOOK_MAIN_INFO);

    private final ILabel lblBookTitleInfo = getElementFactory().getLabel(By.xpath("(//XCUIElementTypeOther//XCUIElementTypeStaticText[@name])[1]"), "Book title");
    private final IButton btnCloseBookDetailsOnlyIOSTab =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton/XCUIElementTypeStaticText[contains(@name, \"Close\")]"), "Close button");
    private final IButton lblErrorDetails =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeAlert//XCUIElementTypeStaticText"), "Error details");

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
    public boolean isActionButtonPresent(EnumActionButtonsForBooksAndAlertsKeys key) {
        AqualityServices.getConditionalWait().waitFor(() ->
                getActionButton(key).state().isDisplayed() || lblErrorDetails.state().isDisplayed(), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        return getActionButton(key).state().isDisplayed();
    }

    @Override
    public void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKeys) {
        IButton actionButton = getActionButton(buttonKeys);
        actionButton.click();
        actionButton.state().waitForNotDisplayed();
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
    public void openErrorDetails() {

    }

    @Override
    public void swipeError() {

    }

    @Override
    public void closeBookDetailsOnlyForIOSTabIfDisplayed() {
        if (btnCloseBookDetailsOnlyIOSTab.state().isDisplayed()) {
            btnCloseBookDetailsOnlyIOSTab.click();
        }
    }

    private IButton getActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKey) {
        String key = buttonKey.i18n();
        return getElementFactory().getButton(By.xpath(String.format(BOOK_ACTION_BUTTON_LOC, key)), key);
    }
}
