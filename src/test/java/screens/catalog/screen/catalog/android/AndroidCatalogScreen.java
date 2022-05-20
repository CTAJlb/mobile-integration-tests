package screens.catalog.screen.catalog.android;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.interfaces.IElement;
import constants.application.attributes.AndroidAttributes;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.catalog.screen.catalog.CatalogScreen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidCatalogScreen extends CatalogScreen {
    private static final String CATEGORY_LOCATOR = "//*[contains(@resource-id, \"feedLaneTitle\") and @text=\"%1$s\"]";
    private static final String LANE_BY_NAME_LOCATOR_PART =
            CATEGORY_LOCATOR + "/following-sibling::*[contains(@resource-id,\"feedLaneCoversScroll\")]";
    private static final String BOOK_COVER_IN_LANE_LOCATOR = "/android.widget.LinearLayout";
    private static final String LIBRARY_BUTTON_LOCATOR_PATTERN =
            "//android.widget.TextView[contains(@resource-id,\"accountTitle\") and @text=\"%s\"]";
    private static final String FEED_LANE_TITLES_LOC = "//*[contains(@resource-id,\"feedLaneTitle\")]";
    private static final String CATEGORY_NAME_XPATH_LOCATOR = "//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout/android.widget.TextView[1]";
    private static final String LIBRARY_NAME_LOC = "//android.widget.TextView[@text=\"%s\" and contains(@resource-id,\"feedLibraryText\")]";
    private static final String BUTTON_MORE_LOCATOR = "//android.widget.LinearLayout/android.widget.TextView[@text=\"More…\"]";
    private static final String BOOK_SECTION_LOCATOR_IN_CATALOG =
            "//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[%d]/android.widget.LinearLayout/android.widget.TextView[1]";
    private static final String SECTION_TITLE = "//android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]";

    private final ILabel lblFirstLaneName = getElementFactory().getLabel(By.xpath(FEED_LANE_TITLES_LOC), "First lane name");
    private final ILabel lblScreen = getElementFactory().getLabel(By.id("mainFragmentHolder"), "Screen to swipe");

    public AndroidCatalogScreen() {
        super(By.id("feedWithGroups"));
    }

    @Override
    public List<String> getListOfBooksNames() {
        List<String> listOfNames = getValuesFromListOfLabels(String.format(LANE_BY_NAME_LOCATOR_PART,
                lblFirstLaneName.getText()) + BOOK_COVER_IN_LANE_LOCATOR);
        AqualityServices.getLogger().info("Found list of books - " + listOfNames.stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
        return listOfNames;
    }

    @Override
    public boolean areCategoryRowsLoaded() {
        return AqualityServices.getConditionalWait().waitFor(() -> getLabels(FEED_LANE_TITLES_LOC).size() > 0);
    }

    @Override
    public void selectLibraryFromListOfAddedLibraries(String libraryName) {
        IButton button = getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_LOCATOR_PATTERN, libraryName)), "Menu");
        button.state().waitForDisplayed();
        button.click();
    }

    @Override
    public void openCategory(String categoryName) {
        IButton categoryButton = getCategoryButton(categoryName);
        categoryButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        categoryButton.click();
    }

    private IButton getCategoryButton(String categoryName) {
        return getElementFactory().getButton(By.xpath(String.format(CATEGORY_LOCATOR, categoryName)), categoryName);
    }

    @Override
    public void switchToCatalogTab(String catalogTab) {
        getElementFactory().getButton(By.xpath(String.format("//android.widget.RadioButton[@text=\"%s\"]", catalogTab)), catalogTab).click();
    }

    @Override
    public Set<String> getListOfAllBooksNamesInFirstLane() {
        return getListOfAllBooksNamesInSubcategoryLane(lblFirstLaneName.getText());
    }

    @Override
    public Set<String> getListOfAllBooksNamesInSubcategoryLane(String lineName) {
        List<String> currentBooksNames = getListOfVisibleBooksNamesInSubcategoryLane(lineName);
        Set<String> bookNames = new HashSet<>();
        ILabel subcategoryLine = getElementFactory().getLabel(
                By.xpath(String.format(LANE_BY_NAME_LOCATOR_PART, lineName)),
                String.format("Subcategory %1$s line", lineName));
        do {
            bookNames.addAll(currentBooksNames);
            SwipeElementUtils.swipeFromRightToLeft(subcategoryLine);
            currentBooksNames = getListOfVisibleBooksNamesInSubcategoryLane(lineName);
        } while (!bookNames.containsAll(currentBooksNames));
        return bookNames;
    }

    @Override
    public boolean isLibraryPresent(String libraryName) {
        return getElementFactory().getLabel(By.xpath(String.format(LIBRARY_NAME_LOC, libraryName)), "labelLibraryName").state().waitForDisplayed();
    }

    @Override
    public Set<String> getAllCategoriesNames() {
        AqualityServices.getConditionalWait().waitFor(() -> getLabels(CATEGORY_NAME_XPATH_LOCATOR).size() > 0);
        List<String> currentCategoriesNames = getListOfCategories();
        Set<String> bookNames = new HashSet<>();
        do {
            bookNames.addAll(currentCategoriesNames);
            SwipeElementUtils.swipeElementUp(lblScreen);
            currentCategoriesNames = getListOfCategories();
        } while (!bookNames.containsAll(currentCategoriesNames));
        return bookNames;
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

    private List<IButton> getMoreBtn() {
        return getElementFactory().findElements(By.xpath(BUTTON_MORE_LOCATOR), ElementType.BUTTON);
    }

    private List<String> getListOfVisibleBooksNamesInSubcategoryLane(String lineName) {
        return getValuesFromListOfLabels(String.format(LANE_BY_NAME_LOCATOR_PART, lineName)
                + BOOK_COVER_IN_LANE_LOCATOR);
    }

    private List<String> getListOfCategories() {
        return getTextFromListOfLabels(CATEGORY_NAME_XPATH_LOCATOR);
    }

    private List<String> getValuesFromListOfLabels(String xpath) {
        return getLabels(xpath)
                .stream()
                .map(x -> x.getAttribute(AndroidAttributes.CONTENT_DESC))
                .collect(Collectors.toList());
    }

    private List<String> getTextFromListOfLabels(String xpath) {
        return getLabels(xpath)
                .stream()
                .map(IElement::getText)
                .collect(Collectors.toList());
    }

    private List<aquality.appium.mobile.elements.interfaces.IElement> getLabels(String xpath) {
        return getElementFactory().findElements(By.xpath(xpath), ElementType.LABEL);
    }
}
