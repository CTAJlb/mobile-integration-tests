package screens.addaccount.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import com.google.common.collect.Ordering;
import framework.utilities.keyboard.KeyboardUtils;
import org.openqa.selenium.By;
import screens.addaccount.AddAccountScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAddAccountScreen extends AddAccountScreen {
    private final IButton btnSearch = getElementFactory().getButton(By.id("accountMenuActionSearch"), "Search");
    private final ITextBox txbSearchField = getElementFactory().getTextBox(By.id("search_src_text"), "Search");
    public static final String LIBRARY_BUTTON_LOCATOR_PATTERN = "//android.widget.TextView[contains(@text, \"%s\")]";
    public static final String LIB_NAME_LOCATOR = "//android.view.ViewGroup//android.widget.TextView[1]";
    public final IButton btnClearText = getElementFactory().getButton(By.id("search_close_btn"), "Clear text button");
    private final ILabel lblAddLibrary = getElementFactory().getLabel(By.xpath("//android.view.ViewGroup/android.widget.TextView[@text=\"Add Library\"]"), "Add Library button");

    public AndroidAddAccountScreen() {
        super(By.id("accountRegistryTitle"));
    }

    @Override
    public void selectLibraryViaSearch(String libraryName) {
        btnSearch.click();
        AqualityServices.getApplication().getDriver().hideKeyboard();
        txbSearchField.clearAndType(libraryName);
        state().waitForDisplayed();
        getLibraryButton(libraryName).click();
    }

    @Override
    public void enterLibraryName(String libraryName) {
        btnSearch.click();
        KeyboardUtils.hideKeyboard();
        txbSearchField.clearAndType(libraryName);
        state().waitForDisplayed();
    }

    @Override
    public boolean isLibraryPresent(String libraryName) {
        return getLibraryButton(libraryName).state().isDisplayed();
    }

    @Override
    public void clearSearchField() {
        btnClearText.click();
    }

    @Override
    public boolean isSearchFieldEmpty() {
        return txbSearchField.getText().equals("Search accounts…");
    }

    @Override
    public boolean isLibraryContainWord(String word) {
        List<String> libraries = getLibrariesNames();
        boolean isContain = true;
        for (String library: libraries) {
            if (!library.contains(word.toLowerCase())) {
                isContain = false;
                break;
            }
        }
        return isContain;
    }

    @Override
    public boolean isSearchResultEmpty() {
        return getLibrariesNames().isEmpty();
    }

    @Override
    public boolean isAddLibScreenOpened() {
        return lblAddLibrary.state().waitForDisplayed();
    }

    @Override
    public boolean isSortingOfLibrariesCorrect() {
        List<String > libraries = getLibrariesNames();
        libraries.remove(0);
        return Ordering.natural().isOrdered(libraries);
    }

    @Override
    public String getTextFromBackBtn() {
        //for ios
        return null;
    }

    @Override
    public String getTextFromAddLibraryLbl() {
        //for ios
        return null;
    }

    private IButton getLibraryButton(String libraryName) {
        return getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_LOCATOR_PATTERN, libraryName)), libraryName);
    }

    private List<String> getLibrariesNames() {
        List<ILabel> libraries = getElementFactory().findElements(By.xpath(LIB_NAME_LOCATOR), ElementType.LABEL);
        List<String> names = new ArrayList<>();
        libraries.forEach(library -> names.add(library.getText().toLowerCase()));
        return names;
    }
}
