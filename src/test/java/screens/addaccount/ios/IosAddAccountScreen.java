package screens.addaccount.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.addaccount.AddAccountScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosAddAccountScreen extends AddAccountScreen {
    private final ITextBox txbSearchField = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeSearchField"), "Search field");
    private static final String MAIN_ELEMENT = "//XCUIElementTypeSheet[@name=\"Add Your Library\"]";
    public static final String LIBRARY_BUTTON_LOCATOR_PATTERN = "//XCUIElementTypeStaticText[contains(@name, \"%s\")]";
    private static final String LIB_NAME_LOCATOR = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[2]";
    private final IButton btnClearText = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Clear text\"]"), "Clear text button");

    public IosAddAccountScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    private IButton getLibraryButton(String libraryName) {
        return getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_LOCATOR_PATTERN, libraryName)), libraryName);
    }

    @Override
    public void selectLibraryViaSearch(String libraryName) {
        txbSearchField.click();
        txbSearchField.clearAndType(libraryName);
        getLibraryButton(libraryName).click();
    }

    @Override
    public void enterLibraryName(String libraryName) {
        txbSearchField.click();
        txbSearchField.clearAndType(libraryName);
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
        return txbSearchField.getText().isEmpty();
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

    public List<String> getLibrariesNames() {
        List<ILabel> buttons = getElementFactory().findElements(By.xpath(LIB_NAME_LOCATOR), ElementType.LABEL);
        List<String> names = new ArrayList<>();
        buttons.forEach(button -> names.add(button.getText().toLowerCase()));
        return names;
    }
}
