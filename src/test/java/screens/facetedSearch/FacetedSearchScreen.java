package screens.facetedSearch;

import aquality.appium.mobile.screens.Screen;
import enums.localization.facetedsearch.FacetAvailabilityKeys;
import enums.localization.facetedsearch.FacetSortByKeys;
import org.openqa.selenium.By;

public abstract class FacetedSearchScreen extends Screen {
    protected FacetedSearchScreen(By locator) {
        super(locator, "Faceted search");
    }

    public abstract void openAvailabilityMenu();

    public abstract void changeAvailabilityTo(FacetAvailabilityKeys key);

    public abstract void openCollection();

    public abstract void sortBy(String library);

    public abstract void changeSortByTo(FacetSortByKeys key);

    public abstract String getTypeVariantsOfBtn (String type);
}
