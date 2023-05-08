package screens.facetedSearch.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.localization.facetedsearch.FacetAvailabilityKeys;
import enums.localization.facetedsearch.FacetSortByKeys;
import org.openqa.selenium.By;
import screens.facetedSearch.FacetedSearchScreen;

import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidFacetedSearchScreen extends FacetedSearchScreen {
    private static final String MAIN_ELEMENT = "//*[contains(@resource-id,\"feedHeaderFacets\")]";
    private static final String FACET_SEARCH_SELECTION = "//*[contains(@resource-id,\"select_dialog_listview\")]"
            + "//*[@text=\"%1$s\"]";

    private final IButton btnSortBy = getElementFactory().getButton(By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.Button"), "Sort by button");
    private final IButton btnAvailability = getElementFactory().getButton(By.xpath(MAIN_ELEMENT + "/android.widget.Button[1]"), "Availability");
    private final BtnGetVariantsOfSorting btnVariantOfSorting = (button ->
            getElementFactory().getButton(By.xpath(String.format("//android.widget.ListView/android.widget.CheckedTextView[@text=\"%s\"]", button)),
                    String.format("%s type of button", button)));

    public AndroidFacetedSearchScreen() {
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
        //only for ios
    }

    @Override
    public void openSortBy() {
        btnSortBy.click();
    }

    @Override
    public List<String> getOptionsInTabs() {
        //for ios
        return null;
    }

    @Override
    public String getTypeVariantsOfBtn(String type) {
        IButton btnTypeOfSorting = btnVariantOfSorting.createBtn(type);
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
    interface BtnGetVariantsOfSorting {
        IButton createBtn(String button);
    }
}
