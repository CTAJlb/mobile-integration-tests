package screens.catalog.screen.catalog.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.Attributes;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import constants.application.attributes.IosAttributes;
import constants.application.timeouts.CategoriesTimeouts;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.catalog.screen.catalog.CatalogScreen;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosCatalogScreen extends CatalogScreen {
    private static final String LANE_BY_NAME_LOCATOR_PART = "(//XCUIElementTypeOther[.//XCUIElementTypeButton[@name=\"%1$s\"]]"
            + "/following-sibling::XCUIElementTypeCell)[1]";
    private static final String BOOK_COVER_IN_LANE_LOCATOR = "/XCUIElementTypeButton";
    private static final String UNIQUE_ELEMENT =
            "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[contains(@name, \"Change Library Account\")]";
    private static final String SPECIFIC_CATEGORY_LOCATOR = UNIQUE_ELEMENT +
            "/parent::XCUIElementTypeNavigationBar/following-sibling::XCUIElementTypeOther//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton[contains(@name, \"%s\")]";
    private static final String CATEGORIES_LOCATOR = UNIQUE_ELEMENT +
            "/parent::XCUIElementTypeNavigationBar/following-sibling::XCUIElementTypeOther//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton[1]";
    private static final String LIBRARY_BUTTON_LOCATOR_PATTERN =
            "//XCUIElementTypeButton[@name=\"%1$s\"]";
    private static final String BOOKS_LOCATOR = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeButton[@name]";
    private static final String CATEGORY_XPATH_PATTERN = "//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeButton[1]";
    private static final String BUTTON_MORE_LOCATOR = "//XCUIElementTypeButton/XCUIElementTypeStaticText[@name=\"More…\"]";
    private static final String BOOK_SECTION_LOCATOR_IN_CATALOG = "//XCUIElementTypeTable/XCUIElementTypeOther[%d]/XCUIElementTypeButton[1]";
    private static final String SECTION_TITLE = "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[@name=\"%s\"]";
    private static final int COUNT_OF_CATEGORIES_TO_WAIT_FOR = 5;

    private final ILabel firstLaneName =
            getElementFactory().getLabel(By.xpath(CATEGORIES_LOCATOR), "First lane name", ElementState.EXISTS_IN_ANY_STATE);
    private final GetNameOfBookTypeBtb btnBookNameTypeSection = (button ->
            getElementFactory().getButton(By.xpath(String.format("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[@name=\"%s\"]", button)),
                    String.format("%s type of sorting", button)));

    public IosCatalogScreen() {
        super(By.xpath(UNIQUE_ELEMENT));
    }

    @Override
    public List<String> getListOfBooksNames() {
        state().waitForDisplayed();
        int countOfItems = getElements(BOOKS_LOCATOR).size();
        AqualityServices.getConditionalWait().waitFor(() -> getElements(BOOKS_LOCATOR).size() <= countOfItems);
        List<String> listOfNames = getValuesFromListOfLabels(BOOKS_LOCATOR);
        AqualityServices.getLogger().info("Found list of books from all subcategories on screen - " + listOfNames.stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
        AqualityServices.getLogger().info("amount of books from all subcategories on screen - " + listOfNames.size());
        return listOfNames;
    }

    @Override
    public boolean areCategoryRowsLoaded() {
        return AqualityServices.getConditionalWait().waitFor(() ->
                        getElementFactory().findElements(By.xpath(CATEGORIES_LOCATOR), ElementType.LABEL).size() > 0,
                Duration.ofMillis(CategoriesTimeouts.TIMEOUT_WAIT_UNTIL_CATEGORY_PAGE_LOAD.getTimeoutMillis()));
    }

    @Override
    public void selectLibraryFromListOfAddedLibraries(String libraryName) {
        getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_LOCATOR_PATTERN, libraryName)),
                "Menu").click();
    }

    @Override
    public void openCategory(String categoryName) {
        IButton categoryButton = getCategoryButton(categoryName);
        if (!categoryButton.state().waitForDisplayed()) {
            categoryButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        categoryButton.click();
    }

    private IButton getCategoryButton(String categoryName) {
        return getElementFactory().getButton(By.xpath(String.format(SPECIFIC_CATEGORY_LOCATOR, categoryName)), categoryName);
    }

    @Override
    public void switchToCatalogTab(String catalogTab) {
        getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_LOCATOR_PATTERN, catalogTab)), catalogTab).click();
        getElementFactory().getLabel(By.xpath("//android.view.ViewGroup[contains(@resource-id, \"bookCellIdle\")"), "BookForm").state().waitForDisplayed();
    }

    @Override
    public Set<String> getListOfAllBooksNamesInFirstLane() {
        return getListOfAllBooksNamesInSubcategoryLane(firstLaneName.getText());
    }

    @Override
    public Set<String> getListOfAllBooksNamesInSubcategoryLane(String lineName) {
        List<String> currentBooksNames = getListOfVisibleBooksNamesInSubcategoryLane(lineName);
        Set<String> bookNames = new HashSet<>();
        ILabel subcategoryLine = getElementFactory().getLabel(
                By.xpath(String.format(LANE_BY_NAME_LOCATOR_PART, lineName)),
                String.format("Subcategory %1$s line", lineName));
        subcategoryLine.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        do {
            bookNames.addAll(currentBooksNames);
            SwipeElementUtils.swipeFromRightToLeft(subcategoryLine);
            currentBooksNames = getListOfVisibleBooksNamesInSubcategoryLane(lineName);
        } while (!bookNames.containsAll(currentBooksNames));
        return bookNames;
    }

    @Override
    public boolean isLibraryPresent(String libraryName) {
        return getElementFactory().getLabel(By.id(libraryName), "labelLibraryName").state().isExist();
    }

    @Override
    public Set<String> getAllCategoriesNames() {
        AqualityServices.getConditionalWait().waitFor(() -> getElements(CATEGORY_XPATH_PATTERN).size() > COUNT_OF_CATEGORIES_TO_WAIT_FOR);
        List<String> currentBooksNames = geListOfCategoriesNames();
        Set<String> categoriesNames = new HashSet<>();
        categoriesNames.addAll(currentBooksNames);
        return categoriesNames;
    }

    @Override
    public boolean isMoreBtnPresent() {
        List<IButton> buttons = getMoreBtn();
        return buttons.stream().allMatch(button -> button.state().isDisplayed());
    }

    @Override
    public String clickToMoreBtn() {
        List<IButton> buttons = getMoreBtn();
        int randomNumber = 1 + (int) (Math.random() * buttons.size());
        String sectionName = getElementFactory().getLabel(By.xpath(String.format(BOOK_SECTION_LOCATOR_IN_CATALOG, randomNumber)), "Book section name").getText();
        buttons.get(randomNumber - 1).click();
        return sectionName;
    }

    @Override
    public boolean isBookSectionOpened(String sectionName) {
        return getElementFactory().getLabel(By.xpath(String.format(SECTION_TITLE, sectionName)), "Section title").state().isDisplayed();
    }

    @Override
    public String getTheNameOfBookTypeBtn(String typeOfBookNameBtn) {
        IButton btnNameOfBookType = btnBookNameTypeSection.createBtn(typeOfBookNameBtn);
        return btnNameOfBookType.getText();
    }

    @Override
    public boolean isSectionWithBookTypeOpen(String typeSection) {
        IButton btnSectionType = btnBookNameTypeSection.createBtn(typeSection);
        return btnSectionType.getAttribute(Attributes.VALUE).equals("1");
    }

    private List<IButton> getMoreBtn() {
        return getElementFactory().findElements(By.xpath(BUTTON_MORE_LOCATOR), ElementType.BUTTON);
    }

    private List<String> geListOfCategoriesNames() {
        return getValuesFromListOfLabels(CATEGORY_XPATH_PATTERN);
    }

    private List<String> getListOfVisibleBooksNamesInSubcategoryLane(String lineName) {
        return getValuesFromListOfLabels(String.format(LANE_BY_NAME_LOCATOR_PART, lineName) + BOOK_COVER_IN_LANE_LOCATOR);
    }

    private List<String> getValuesFromListOfLabels(String xpath) {
        return getElements(xpath)
                .stream()
                .map(x -> x.getAttribute(IosAttributes.NAME))
                .collect(Collectors.toList());
    }

    private List<IElement> getElements(String xpath) {
        return getElementFactory().findElements(By.xpath(xpath), ElementType.LABEL, ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    }

    @FunctionalInterface
    interface GetNameOfBookTypeBtb {
        IButton createBtn(String button);
    }
}
