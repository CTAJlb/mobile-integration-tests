package screens.addaccount.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.addaccount.AddAccountScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAddAccountScreen extends AddAccountScreen {
    private IButton btnSearch = getElementFactory().getButton(By.id("accountMenuActionSearch"), "Search");
    private ITextBox txbSearch = getElementFactory().getTextBox(By.id("search_src_text"), "Search");
    public static final String LIBRARY_BUTTON_LOCATOR_PATTERN = "//android.widget.TextView[contains(@text, \"%s\")]";

    public AndroidAddAccountScreen() {
        super(By.id("accountRegistryTitle"));
    }

    @Override
    public void selectLibraryViaSearch(String libraryName) {
        btnSearch.click();
        AqualityServices.getApplication().getDriver().hideKeyboard();
        txbSearch.clearAndType(libraryName);
        state().waitForDisplayed();
        getLibraryButton(libraryName).click();
    }

    @Override
    public void enterLibraryName(String libraryName) {

    }

    @Override
    public boolean isLibraryPresent(String libraryName) {
        return true;
    }

    @Override
    public void clearSearchField() {

    }

    @Override
    public boolean isSearchFieldEmpty() {
return true;
    }

    @Override
    public boolean isLibraryContainWord(String word) {
        return false;
    }

    @Override
    public boolean isSearchResultEmpty() {
        return false;
    }

    private IButton getLibraryButton(String libraryName) {
        return getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_LOCATOR_PATTERN, libraryName)), libraryName);
    }
}
