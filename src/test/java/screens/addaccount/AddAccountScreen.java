package screens.addaccount;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class AddAccountScreen extends Screen {
    protected AddAccountScreen(By locator) {
        super(locator, "Add Accounts");
    }

    public abstract void selectLibraryViaSearch(String libraryName);

    public abstract void enterLibraryName(String libraryName);

    public abstract boolean isLibraryPresent(String libraryName);

    public abstract void clearSearchField();

    public abstract boolean isSearchFieldEmpty();

    public abstract boolean isLibraryContainWord(String word);

    public abstract boolean isSearchResultEmpty();

    public abstract boolean isAddLibScreenOpened();

    public abstract boolean isSortingOfLibrariesCorrect();
}
