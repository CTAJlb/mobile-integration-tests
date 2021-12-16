package screens.bookDetails.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.swipe.SwipeElementUtils;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.bookDetails.BookDetailsScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBookDetailsScreen extends BookDetailsScreen {
    private static final String BOOK_ACTION_BUTTON_LOC = "//android.widget.Button[@text=\"%s\"]";
    private final ILabel lblErrorScreen = getElementFactory().getLabel(By.xpath("//android.widget.ScrollView"), "Error Screen");
    private final ILabel lblBookTitleInfo = getElementFactory().getLabel(By.id("bookDetailTitle"), "Book title");
    private final ILabel lblBookAuthorsInfo = getElementFactory().getLabel(By.id("bookDetailAuthors"), "Book Authors");
    private final ILabel lblErrorMessage = getElementFactory().getLabel(By.id("errorDetails"), "Error message");

    private final IButton btnErrorDetails =
            getElementFactory().getButton(By.xpath("//*[contains(@resource-id,'bookDetailButtons')]//*[contains(@text,'Details')]"), "Error");

    public AndroidBookDetailsScreen() {
        super(By.id("bookDetailCover"));
    }

    @Override
    public CatalogBookModel getBookInfo() {
        return new CatalogBookModel()
                .setTitle(lblBookTitleInfo.getText())
                .setAuthor(lblBookAuthorsInfo.getText());
    }

    @Override
    public boolean isActionButtonDisplayed(EnumActionButtonsForBooksAndAlertsKeys key) {
        IButton button = getActionButton(key);
        return button.state().isDisplayed();
    }

    @Override
    public void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKeys) {
        IButton button = getActionButton(buttonKeys);
        button.state().waitForDisplayed();
        button.click();
    }

    @Override
    public String getErrorDetails() {
        if (lblErrorMessage.state().isDisplayed()) {
            return lblErrorMessage.getText();
        } else {
            return "";
        }
    }

    @Override
    public boolean isErrorButtonPresent() {
        return btnErrorDetails.state().isDisplayed();
    }

    @Override
    public void openErrorDetails() {
        btnErrorDetails.click();
    }

    @Override
    public void swipeError() {
        SwipeElementUtils.swipeThroughEntireElementUp(lblErrorScreen);
    }

    @Override
    public void closeBookDetailsOnlyForIOSTabIfDisplayed() {
        //only for ios
    }

    private IButton getActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKey) {
        String key = buttonKey.i18n();
        return getElementFactory().getButton(By.xpath(String.format(BOOK_ACTION_BUTTON_LOC, key)), key);
    }
}
