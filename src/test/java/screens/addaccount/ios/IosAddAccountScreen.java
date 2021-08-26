package screens.addaccount.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.addaccount.AddAccountScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosAddAccountScreen extends AddAccountScreen {
    private ITextBox txbSearch = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeSearchField"), "Search");
    private static final String MAIN_ELEMENT = "//XCUIElementTypeSheet[@name=\"Add Your Library\"]";
    public static final String LIBRARY_BUTTON_LOCATOR_PATTERN = "//XCUIElementTypeStaticText[contains(@name, \"%s\")]";

    public IosAddAccountScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    private IButton getLibraryButton(String libraryName) {
        return getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_LOCATOR_PATTERN, libraryName)), libraryName);
    }

    @Override
    public void selectLibraryViaSearch(String libraryName) {
        txbSearch.click();
        AqualityServices.getApplication().getDriver().hideKeyboard();
        txbSearch.clearAndType(libraryName);
        getLibraryButton(libraryName).click();
    }

    private IButton getLibraryButton(String libraryName, String libraryButtonPattern) {
        return getElementFactory().getButton(By.xpath(String.format(libraryButtonPattern, libraryName)), libraryName);
    }
}
