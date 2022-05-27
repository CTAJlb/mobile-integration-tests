package screens.catalog.screen.catalog;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Set;

public abstract class CatalogScreen extends Screen {
    protected CatalogScreen(By locator) {
        super(locator, "Catalog");
    }

    public abstract List<String> getListOfBooksNames();

    public abstract boolean areCategoryRowsLoaded();

    public abstract void selectLibraryFromListOfAddedLibraries(String libraryName);

    public abstract void openCategory(String categoryName);

    public abstract Set<String> getListOfAllBooksNamesInFirstLane();

    public abstract void switchToCatalogTab(String catalogTab);

    public abstract Set<String> getListOfAllBooksNamesInSubcategoryLane(String lineName);

    public abstract boolean isLibraryPresent(String libraryName);

    public abstract Set<String> getAllCategoriesNames();

    public abstract boolean isMoreBtnPresent();

    public abstract String clickToMoreBtn();

    public abstract boolean isBookSectionOpened(String sectionName);

    public abstract String getTheNameOfBookTypeBtn(String typeOfBookNameBtn);

    public abstract void tapTheLogo();

    public abstract boolean isSectionWithBookTypeOpen(String typeSection);
}
