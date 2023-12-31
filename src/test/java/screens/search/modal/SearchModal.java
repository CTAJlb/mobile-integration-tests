package screens.search.modal;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class SearchModal extends Screen {
    protected SearchModal(By locator) {
        super(locator, "Search modal");
    }

    public abstract void setSearchedText(String text);

    public abstract void inputCharacterK();

    public abstract void applySearch();

    public abstract void clearSearchField();

    public abstract boolean isSearchFieldEmpty();

    public abstract void closeSearchScreen();

    public abstract String getTextFromBackButton();

    public abstract String getTextFromSearchField();

    public abstract void deleteSomeData();

    public abstract boolean isSearchButtonClickable();

    public abstract boolean isSearchLineDisplayed();

    public abstract void inputSpace();
}
