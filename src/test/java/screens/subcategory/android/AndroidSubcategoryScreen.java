package screens.subcategory.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.interfaces.IElement;
import constants.applicationattributes.AndroidAttributes;
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

    private final ILabel lblFirstBookImageCover =
            getElementFactory().getLabel(By.xpath(BOOKS_LOCATOR), "First book image info");
    private final ILabel lblFirstBookTitle =
            getElementFactory().getLabel(By.xpath(BOOK_NAME_XPATH), "First book title");
    private final ILabel lblFirstBookAuthor =
            getElementFactory().getLabel(By.xpath(AUTHOR_INFO_XPATH), "First book author");
    private final ILabel lblErrorDetails = getElementFactory().getLabel(By.id("errorDetails"), "Error details");
    private final IButton btnErrorDetails = getElementFactory().getButton(By.id("bookCellErrorButtonDetails"), "Error details");
    private final IButton btnFeedErrorDetails = getElementFactory().getButton(By.id("feedErrorDetails"), "Error details");
    private final IButton btnSorting =
            getElementFactory().getButton(By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.Button[2]"), "Sort button");
    private final IButton btnSortingPalace =
            getElementFactory().getButton(By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.Button"), "Sort button");
    private final IButton btnBack
            = getElementFactory().getButton(By.xpath("//android.view.ViewGroup[contains(@resource-id, \"mainToolbar\")]/android.widget.ImageView"), "Back button");
    private final IButton btnAvailability =
            getElementFactory().getButton(By.xpath("//android.widget.HorizontalScrollView//android.widget.Button[1]"), "Availability button");

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
    public String getNameOfSorting(String library) {
        if(library.equals("Palace Bookshelf")) {
            return btnSortingPalace.getText();
        }
        return btnSorting.getText();
    }

    @Override
    public void tapBack() {
        btnBack.click();
    }

    @Override
    public String getAvailability() {
        return btnAvailability.getText();
    }

    @Override
    public String getCollectionName() {
        //only for ios
        return null;
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
