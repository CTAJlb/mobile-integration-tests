package screens.subcategory;

import aquality.appium.mobile.screens.Screen;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

import java.util.List;

public abstract class SubcategoryScreen extends Screen {
    protected SubcategoryScreen(By locator) {
        super(locator, "Subcategory");
    }

    public abstract CatalogBookModel getFirstBookInfo();

    public abstract void openFirstBook();

    public abstract boolean areSubcategoryRowsLoaded();

    public abstract List<String> getBooksInfo();

    public abstract List<String> getAllButtonsNames();

    public abstract List<String> getAuthorsInfo();

    public abstract List<String> getTitlesInfo();

    public abstract String getErrorMessage();

    public abstract void openCategory(String categoryName);

    public abstract boolean isErrorButtonPresent();

    public abstract String getNameOfSorting();

    public abstract void tapBack();

    public abstract String getAvailability();

    public abstract String getCollectionName();
}
