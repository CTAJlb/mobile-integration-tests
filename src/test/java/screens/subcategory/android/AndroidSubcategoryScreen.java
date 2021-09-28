package screens.subcategory.android;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.interfaces.IElement;
import constants.application.attributes.AndroidAttributes;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.subcategory.SubcategoryScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSubcategoryScreen extends SubcategoryScreen {
    private static final String BOOKS_LOCATOR = "//android.widget.ImageView[contains(@resource-id,\"bookCellIdleCover\")]";
    private static final String SPECIFIC_SUBCATEGORY_LOCATOR = "//*[contains(@resource-id, \"feedLaneTitle\") and @text=\"%1$s\"]";
    private static final String FEED_LANE_TITLES_LOC = "//*[contains(@resource-id,\"feedLaneTitle\")]";
    public static final String BOOK_BUTTON_XPATH =
            "//android.widget.LinearLayout[contains(@resource-id,\"bookCellIdleButtons\")]/android.widget.Button";
    private static final String AUTHOR_INFO_XPATH =
            "//android.widget.TextView[contains(@resource-id, \"bookCellIdleAuthor\")]";
    private static final String BOOK_NAME_XPATH =
            "//android.widget.TextView[contains(@resource-id, \"bookCellIdleTitle\")]";
    private static final String TYPE_INFO_XPATH =
            "//android.widget.TextView[contains(@resource-id, \"bookCellIdleMeta\")]";
    public static final String BOOK_BUTTON_WITH_DEFINITE_NAME_AND_DEFINITE_ACTION_BUTTON_LOCATOR_PATTERN = "//android.widget.TextView[contains(@resource-id, \"bookCellIdleTitle\") and @text=\"%s\"]/following-sibling::android.widget.LinearLayout//android.widget.Button[@text=\"%s\"]";
    public static final String BOOK_TITLE_WITH_DEFINITE_NAME_AND_DEFINITE_ACTION_BUTTON_ENDING_PART_LOCATOR_PATTERN = "/parent::android.widget.LinearLayout/preceding-sibling::android.widget.TextView[contains(@resource-id, \"bookCellIdleTitle\")]";
    public static final String AUTHOR_LABEL_LOCATOR_PART = "/parent::android.widget.LinearLayout/preceding-sibling::android.widget.TextView[contains(@resource-id, \"bookCellIdleAuthor\")]";
    private static final String SPECIFIC_ACTION_BUTTON_ON_SPECIFIC_BOOK_LOC = "//android.widget.TextView[contains(@text,\"%s\")]/following-sibling::android.widget.LinearLayout/android.widget.Button[@text=\"%s\"]";
    private static final String SPECIFIC_BOOK_NAME_LOC = "//android.widget.TextView[contains(@text,\"%s\")]";

    private final ILabel lblFirstBookImageCover =
            getElementFactory().getLabel(By.xpath(BOOKS_LOCATOR), "First book image info");
    private final ILabel lblFirstBookTitle =
            getElementFactory().getLabel(By.xpath(BOOK_NAME_XPATH), "First book title");
    private final ILabel lblFirstBookAuthor =
            getElementFactory().getLabel(By.xpath(AUTHOR_INFO_XPATH), "First book author");
    private final ILabel lblErrorDetails = getElementFactory().getLabel(By.id("errorDetails"), "Error details");
    private final IButton btnErrorDetails = getElementFactory().getButton(By.id("bookCellErrorButtonDetails"), "Error details");
    private final IButton btnFeedErrorDetails = getElementFactory().getButton(By.id("feedErrorDetails"), "Error details");

    public AndroidSubcategoryScreen() {
        super(By.xpath("//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"feedWithoutGroupsList\")]"));
    }

    @Override
    public List<String> getBooksInfo() {
        List<String> listOfNames = getElementFactory().findElements(By.xpath(BOOKS_LOCATOR), ElementType.LABEL)
                .stream()
                .map(x -> x.getAttribute(AndroidAttributes.CONTENT_DESC))
                .collect(Collectors.toList());
        AqualityServices.getLogger().info("Found list of books - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return listOfNames;
    }

    @Override
    public List<String> getAllButtonsNames() {
        List<String> listOfNames = getValuesFromListOfLabels(BOOK_BUTTON_XPATH);
        AqualityServices.getLogger().info("Found list of buttons names - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return listOfNames;
    }

    @Override
    public List<String> getTitlesInfo() {
        List<String> listOfNames = getValuesFromListOfLabels(BOOK_NAME_XPATH);
        AqualityServices.getLogger().info("Found list of titles - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return listOfNames;
    }

    @Override
    public void openBookWithDefiniteNameAndDefiniteActionButton(CatalogBookModel bookInfo, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String bookName = bookInfo.getTitle();
        String actionButtonString = actionButtonKey.i18n();
        IButton actionButton = getElementFactory().getButton(By.xpath(String.format(SPECIFIC_ACTION_BUTTON_ON_SPECIFIC_BOOK_LOC, bookName, actionButtonString)), "Action Button");
        if (!actionButton.state().waitForDisplayed()) {
            actionButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        if (actionButton.state().isDisplayed()) {
            getElementFactory().getButton(By.xpath(String.format(SPECIFIC_BOOK_NAME_LOC, bookName)), bookName).click();
        } else {
            throw new RuntimeException("There is not book with action button and title-" + bookName);
        }
    }

    @Override
    public CatalogBookModel openBookWithDefiniteActionButtonAndDefiniteNameAndDefiniteBookTypeFromAPIAndGetBookInfo(String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookType) {
        String actionButton = "";
        if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.GET) {
            actionButton = "Get";
        } else if (actionButtonKey == EnumActionButtonsForBooksAndAlertsKeys.RESERVE) {
            actionButton = "Reserve";
        }
        String locator = String.format(BOOK_BUTTON_WITH_DEFINITE_NAME_AND_DEFINITE_ACTION_BUTTON_LOCATOR_PATTERN, bookName, actionButton);

        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(getElementFactory().getButton(By.xpath(locator + AUTHOR_LABEL_LOCATOR_PART), bookName).getText());
        if (getElementFactory().getButton(By.xpath(locator), bookName).state().isDisplayed()) {
            getElementFactory().getButton(By.xpath(locator + BOOK_TITLE_WITH_DEFINITE_NAME_AND_DEFINITE_ACTION_BUTTON_ENDING_PART_LOCATOR_PATTERN), bookName).click();
        } else {
            throw new RuntimeException("There is not book with title-" + bookName + " and button-" + actionButton);
        }
        return bookInfo;
    }

    @Override
    public String getErrorMessage() {
        if (isErrorDetailsButtonDisplayed()) {
            btnErrorDetails.click();
            return getErrorDetails();
        } else if (isFeedErrorDetailsButtonDisplayed()) {
            btnFeedErrorDetails.click();
            return getErrorDetails();
        }
        AqualityServices.getLogger().info("Error details button is not present");
        return "";
    }

    @Override
    public void openCategory(String categoryName) {
        IButton categoryButton = getCategoryButton(categoryName);
        categoryButton.click();
    }

    private IButton getCategoryButton(String categoryName) {
        return getElementFactory().getButton(By.xpath(String.format(SPECIFIC_SUBCATEGORY_LOCATOR, categoryName)), categoryName);
    }

    @Override
    public boolean isErrorButtonPresent() {
        return isErrorDetailsButtonDisplayed() || isFeedErrorDetailsButtonDisplayed();
    }

    @Override
    public List<String> getAuthorsInfo() {
        List<String> listOfNames = getValuesFromListOfLabels(AUTHOR_INFO_XPATH);
        AqualityServices.getLogger().info("Found list of authors - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return listOfNames;
    }

    private List<String> getValuesFromListOfLabels(String xpath) {
        return getElementFactory().findElements(By.xpath(xpath), ElementType.LABEL)
                .stream()
                .map(IElement::getText)
                .collect(Collectors.toList());
    }

    @Override
    public CatalogBookModel getFirstBookInfo() {
        return new CatalogBookModel()
                .setTitle(lblFirstBookTitle.getText())
                .setAuthor(lblFirstBookAuthor.getText());
    }

    @Override
    public void openFirstBook() {
        lblFirstBookImageCover.click();
    }

    @Override
    public boolean areSubcategoryRowsLoaded() {
        return AqualityServices.getConditionalWait().waitFor(() -> getLabels(FEED_LANE_TITLES_LOC).size() > 0);
    }

    private List<aquality.appium.mobile.elements.interfaces.IElement> getLabels(String xpath) {
        return getElementFactory().findElements(By.xpath(xpath), ElementType.LABEL);
    }

    private boolean isFeedErrorDetailsButtonDisplayed() {
        return btnFeedErrorDetails.state().isDisplayed();
    }

    private boolean isErrorDetailsButtonDisplayed() {
        return btnErrorDetails.state().isDisplayed();
    }

    private String getErrorDetails() {
        lblErrorDetails.state().waitForDisplayed();
        return lblErrorDetails.getText();
    }
}
