package screens.subcategory.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.interfaces.IElement;
import constants.application.timeouts.CategoriesTimeouts;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.subcategory.SubcategoryScreen;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosSubcategoryScreen extends SubcategoryScreen {
    private static final String BOOKS_LOCATOR = "//XCUIElementTypeCell";
    private static final String SUBCATEGORY_ROWS_LOCATOR = "//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton[@name]";
    private static final String SPECIFIC_SUBCATEGORY_LOCATOR = "//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton[contains(@name, \"%s\")]";
    private static final String BOOK_BUTTON_XPATH = BOOKS_LOCATOR + "//XCUIElementTypeButton";
    private static final String AUTHOR_INFO_XPATH = "//XCUIElementTypeStaticText[@name][2]";
    private static final String BOOK_NAME_XPATH = "//XCUIElementTypeStaticText[1]";
    private static final int COUNT_OF_ITEMS_TO_WAIT_FOR = 3;

    private final ILabel lblFirstBookName =
            getElementFactory().getLabel(By.xpath(BOOKS_LOCATOR + BOOK_NAME_XPATH), "First book name");
    private final ILabel lblFirstBookAuthor =
            getElementFactory().getLabel(By.xpath(BOOKS_LOCATOR + AUTHOR_INFO_XPATH), "First book author");
    private final IButton btnSorting =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[1]"), "Sort button");
    private final IButton btnBack = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Back\"]"), "Back button");
    private final IButton btnAvailability =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[2]"), "Availability button");
    private final IButton btnCollection =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[3]"), "Collection button");

    public IosSubcategoryScreen() {
        super(By.xpath("//XCUIElementTypeCollectionView"));
    }

    @Override
    public List<String> getBooksInfo() {
        List<String> listOfNames = getElements(BOOKS_LOCATOR + BOOK_NAME_XPATH, ElementType.LABEL)
                .stream()
                .map(IElement::getText)
                .collect(Collectors.toList());
        AqualityServices.getLogger().info("Found list of books - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return listOfNames;
    }

    @Override
    public List<String> getAllButtonsNames() {
        List<String> listOfNames = getValuesFromListOfLabels(BOOKS_LOCATOR + BOOK_BUTTON_XPATH);
        AqualityServices.getLogger().info("Found list of buttons names - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining("; ")));
        return listOfNames;
    }

    @Override
    public List<String> getTitlesInfo() {
        List<String> listOfNames = getValuesFromListOfLabels(BOOKS_LOCATOR + BOOK_NAME_XPATH);
        AqualityServices.getLogger().info("Found list of titles - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining("; ")));
        return listOfNames;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public void openCategory(String categoryName) {
        IButton categoryButton = getCategoryButton(categoryName);
        categoryButton.state().waitForDisplayed();
        categoryButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        categoryButton.click();
    }

    private IButton getCategoryButton(String categoryName) {
        return getElementFactory().getButton(By.xpath(String.format(SPECIFIC_SUBCATEGORY_LOCATOR, categoryName)), categoryName);
    }

    @Override
    public boolean isErrorButtonPresent() {
        return false;
    }

    @Override
    public String getNameOfSorting(String library) {
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
        return btnCollection.getText();
    }

    @Override
    public List<String> getAuthorsInfo() {
        AqualityServices.getConditionalWait().waitFor(() -> getElements(BOOKS_LOCATOR + AUTHOR_INFO_XPATH, ElementType.LABEL).size() > COUNT_OF_ITEMS_TO_WAIT_FOR);
        List<String> listOfNames = getValuesFromListOfLabels(BOOKS_LOCATOR + AUTHOR_INFO_XPATH);
        AqualityServices.getLogger().info("Found list of authors - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining("; ")));
        return listOfNames;
    }

    @Override
    public CatalogBookModel getFirstBookInfo() {
        return new CatalogBookModel()
                .setTitle(lblFirstBookName.getText())
                .setAuthor(lblFirstBookAuthor.getText());
    }

    @Override
    public void openFirstBook() {
        lblFirstBookName.click();
    }

    @Override
    public boolean areSubcategoryRowsLoaded() {
        return AqualityServices.getConditionalWait().waitFor(() ->
                        getElementFactory().findElements(By.xpath(SUBCATEGORY_ROWS_LOCATOR), ElementType.LABEL).size() > 0,
                Duration.ofMillis(CategoriesTimeouts.TIMEOUT_WAIT_UNTIL_CATEGORY_PAGE_LOAD.getTimeoutMillis()));
    }

    private List<String> getValuesFromListOfLabels(String xpath) {
        return getElements(xpath, ElementType.LABEL)
                .stream()
                .map(IElement::getText)
                .collect(Collectors.toList());
    }

    private List<aquality.appium.mobile.elements.interfaces.IElement> getElements(String xpath, ElementType label) {
        return getElementFactory().findElements(By.xpath(xpath), label);
    }
}
