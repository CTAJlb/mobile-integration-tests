package screens.bookDetails.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.timeouts.BooksTimeouts;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.swipe.SwipeElementUtils;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.bookDetails.BookDetailsScreen;

import java.time.Duration;
import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBookDetailsScreen extends BookDetailsScreen {
    private static final String BOOK_ACTION_BUTTON_LOC = "//android.widget.Button[@text=\"%s\"]";
    private static final String LBL_AUTHOR_IN_RELATED_BOOKS = "//android.widget.FrameLayout//android.widget.TextView[@text=\"%s\"]";
    private static final String LBL_LIST_OF_RELATED_BOOKS = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id, \"feedLaneCoversScroll\")]/android.widget.LinearLayout";

    private final ILabel lblErrorScreen = getElementFactory().getLabel(By.xpath("//android.widget.ScrollView"), "Error Screen");
    private final ILabel lblPublished = getElementFactory().getLabel(By.xpath("//android.widget.LinearLayout//android.widget.TextView[contains(@text,\"Published\")]/following-sibling::android.widget.TextView"), "lblPublished");
    private final ILabel lblBookFormat = getElementFactory().getLabel(By.xpath("//android.widget.LinearLayout//android.widget.TextView[@text=\"Format\"]/following::android.widget.TextView"), "Book format label");
    private final ILabel lblPublisher = getElementFactory().getLabel(By.xpath("//android.widget.LinearLayout//android.widget.TextView[contains(@text,\"Publisher\")]/following::android.widget.TextView"), "Publisher label");
    private final ILabel lblCategories = getElementFactory().getLabel(By.xpath("//android.widget.LinearLayout//android.widget.TextView[contains(@text,\"Categor\")]/following::android.widget.TextView"), "Categories label");
    private final ILabel lblDistributor = getElementFactory().getLabel(By.xpath("//android.widget.LinearLayout//android.widget.TextView[contains(@text,\"Distributor\")]/following::android.widget.TextView"), "Distributor label");
    private final ILabel lblBookTitleInfo = getElementFactory().getLabel(By.id("bookDetailTitle"), "Book title");
    private final ILabel lblProgressBar = getElementFactory().getLabel(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"bookDetailStatusInProgress\")]"), "lblProgressBar");
    private final ILabel lblBookAuthorsInfo = getElementFactory().getLabel(By.id("bookDetailAuthors"), "Book Authors");
    private final ILabel lblErrorMessage = getElementFactory().getLabel(By.id("errorDetails"), "Error message");
    private final IButton btnErrorDetails = getElementFactory().getButton(By.xpath("//*[contains(@resource-id,'bookDetailButtons')]//*[contains(@text,'Details')]"), "Error");
    private final ILabel lblBookCover = getElementFactory().getLabel(By.xpath("//android.widget.ImageView[contains(@resource-id, \"bookDetailCoverImage\")]"), "Book cover");
    private final ILabel lblTextInDescription = getElementFactory().getLabel(By.xpath("//android.widget.TextView[@text=\"Description\"]/following::android.widget.TextView"), "Info in description section");
    private final IButton btnMoreInRelatedBooks = getElementFactory().getButton(By.xpath("//android.widget.FrameLayout//android.widget.TextView[@text=\"More…\"]"), "More button in related books section");
    private final IButton btnMoreInDescription = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Description\"]//following::android.widget.TextView[@text=\"More…\"]"), "More button");

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
        return button.state().waitForDisplayed();
    }

    @Override
    public void clickActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKeys) {
        IButton button = getActionButton(buttonKeys);
        button.state().waitForDisplayed();
        button.click();
        if (buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.GET || buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.REMOVE
                || buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.DELETE || buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.RETURN
                || buttonKeys == EnumActionButtonsForBooksAndAlertsKeys.RESERVE) {
            AqualityServices.getConditionalWait().waitFor(() -> !isProgressBarDisplayed(), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        }
    }

    @Override
    public void clickActionButtonForCancelTheAction(EnumActionButtonsForBooksAndAlertsKeys buttonKeys) {
        IButton button = getActionButton(buttonKeys);
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
    public boolean isProgressBarDisplayed() {
        return lblProgressBar.state().isDisplayed();
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
    public String getPublishedInfo() {
        return lblPublished.getText();
    }

    @Override
    public boolean isBookFormatInfoExist() {
        return lblBookFormat.state().waitForDisplayed();
    }

    @Override
    public String getBookFormatInfo() {
        return lblBookFormat.getText();
    }

    @Override
    public String getPublisherInfo() {
        return lblPublisher.getText();
    }

    @Override
    public boolean isPublisherInfoExist() {
        return lblPublisher.state().waitForDisplayed();
    }

    @Override
    public String getCategoryInfo() {
        lblCategories.state().waitForDisplayed();
        return lblCategories.getText();
    }

    @Override
    public boolean isCategoryInfoExist() {
        return lblCategories.state().waitForDisplayed();
    }

    @Override
    public String getDistributorInfo() {
        return lblDistributor.getText();
    }

    @Override
    public void closeBookDetailsOnlyForIOSTabIfDisplayed() {
        //only for ios
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

    @Override
    public String getTextFromBackBtn() {
        return null;
    }

    @Override
    public String getTextFromLabelAboutAvailability() {
        return null;
    }

    @Override
    public String getTextFromDescriptionLbl() {
        return null;
    }

    @Override
    public String getTextFromInformationLbl() {
        return null;
    }

    @Override
    public String getTextFromPublishedLbl() {
        return null;
    }

    @Override
    public String getTextFromPublisherLbl() {
        return null;
    }

    private IButton getActionButton(EnumActionButtonsForBooksAndAlertsKeys buttonKey) {
        String key = buttonKey.getDefaultLocalizedValue();
        return getElementFactory().getButton(By.xpath(String.format(BOOK_ACTION_BUTTON_LOC, key)), key);
    }
}
