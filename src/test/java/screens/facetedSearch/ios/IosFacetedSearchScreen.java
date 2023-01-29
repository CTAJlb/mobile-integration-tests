package screens.facetedSearch.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.localization.facetedsearch.FacetAvailabilityKeys;
import enums.localization.facetedsearch.FacetSortByKeys;
import org.openqa.selenium.By;
import screens.facetedSearch.FacetedSearchScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosFacetedSearchScreen extends FacetedSearchScreen {

    private static final String MAIN_ELEMENT = "//XCUIElementTypeCollectionView/preceding-sibling::XCUIElementTypeOther";
    private static final String FACET_SEARCH_SELECTION = "//XCUIElementTypeButton[@name=\"%1$s\"]";
    private static final String OPTIONS_IN_TABS_LOCATOR = "//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeScrollView//XCUIElementTypeButton";
    private final IButton btnSorting = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[1]"), "Sort by button");
    private final IButton btnAvailability = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[2]"), "Availability button");
    private final IButton btnSortBy = getElementFactory().getButton(By.xpath(String.format("(%1$s//XCUIElementTypeButton)[1]", MAIN_ELEMENT)), "Sort by");
    private final IButton btnCollection = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[3]"), "Collection button");

    private final CreatingVariantsOfButton variantsOfButton = (button ->
            getElementFactory().getButton(By.xpath(String.format("//XCUIElementTypeOther//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"%s\"]", button)),
                    String.format("%s type of button", button)));

    public IosFacetedSearchScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public void openAvailabilityMenu() {
        btnAvailability.click();
    }

    @Override
    public void changeAvailabilityTo(FacetAvailabilityKeys key) {
        setFacetSearchSelection(key.getDefaultLocalizedValue());
    }

    @Override
    public void openCollection() {
        btnCollection.click();
    }

    @Override
    public void openSortBy() {
        btnSorting.click();
    }

    @Override
    public List<String> getOptionsInTabs() {
        List<String> options = new ArrayList<>();
        getElementFactory().findElements(By.xpath(OPTIONS_IN_TABS_LOCATOR), ElementType.BUTTON).forEach(option -> options.add(option.getText()));
        return options;
    }

    @Override
    public void sortBy(String library) {
        btnSortBy.click();
    }

    @Override
    public String getTypeVariantsOfBtn(String type) {
        IButton btnTypeOfSorting = variantsOfButton.createBtn(type);
        return btnTypeOfSorting.getText();
    }

    @Override
    public void changeSortByTo(FacetSortByKeys key) {
        setFacetSearchSelection(key.getDefaultLocalizedValue());
    }

    private void setFacetSearchSelection(String value) {
        getElementFactory().getButton(By.xpath(String.format(FACET_SEARCH_SELECTION, value)),
                "Facet search value " + value).click();
    }

    @FunctionalInterface
    interface CreatingVariantsOfButton {
        IButton createBtn(String button);
    }
}
